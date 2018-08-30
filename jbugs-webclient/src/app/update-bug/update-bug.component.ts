import {Component, OnInit} from '@angular/core';
import {Bug} from "../types/bugs";
import {BugService} from "../services/bug.service";
import {Attachment} from "../types/attachment";
import {User} from "../types/user";
import {UserService} from "../services/user.service";
import {BugDataService} from "../services/bugData.service";
import {BugFilter} from "../types/bug-filter";
import {BugFilterChoice} from "../types/bug-filter-options";
import {BugListHeader} from "../types/bug-list-header";
import {BugSortService} from "../services/bug-sort.service";
import {FilterDataService} from "../services/filter-data.service";
import {ExcelService} from "../services/excel.service";
import {HistoryClass} from "../types/history";
import {FilterService} from "../services/filter.service";

@Component({
  selector: 'app-update-bug',
  templateUrl: './update-bug.component.html',
  styleUrls: ['./update-bug.component.css']
})
export class UpdateBugComponent implements OnInit {
  page: number = 1;
  bugs: Bug[] = [];
  isEditable: boolean[] = [];
  allUsers: User[] = [];
  endDate: string;
  severity: string[] = ["CRITICAL", "HIGH", "MEDIUM", "LOW"];
  chosenSeverity: string;
  statuses: string[] = ["fixed", "open", "in_progress", "rejected", "info_needed", "closed"];
  chosenStatus: string;
  bug: Bug;
  statusMap = new Map();
  updatedStatus : string[];
  attachments: Attachment[];
  attachmentsForABug: Attachment[];
  attachmentChosen:Attachment;
  attachmentToBeAdded: Attachment;
  filters: BugFilter[] = [];
  header: BugListHeader[] = [];
  history: HistoryClass = new HistoryClass();

  constructor(public filterDataService: FilterDataService, private bugService: BugService, private userService: UserService, private dataService: BugDataService, private sortService: BugSortService, private excelService: ExcelService,private filterService: FilterService) {
    this.attachmentToBeAdded = {
      bugDTO:null,
      blob: null,
      extension:'',
      name:''
    }
  }

  ngOnInit(): void {
    if(typeof this.filterDataService.filterList !== 'undefined' && this.filterDataService.filterList.length > 0){
      this.filters = Array.from(this.filterDataService.filterList);
    }


    this.bugService.filterBugs(this.filters).subscribe(
      (response: Bug[]) => {
        this.bugs = [];
        response.forEach((value) => {
          this.isEditable[value.idBug] = false;
          this.bugs.push(value);
        });
      }
    );
    this.bugs.forEach((value) => {
      this.isEditable[value.idBug] = false;
    });
    this.createHeader();
    this.createMap();
    this.attachments = this.bugService.getAllAttachments();
    this.userService.getUsers().subscribe((response:User[])=>{
        response.forEach((value => {
          this.allUsers.push(value);
        }))
    })
  }

  createHeader(){
    var headerNames = ["title","version", "fixed version", "severity", "status", "assigned to", "target date", "created by"];
    for(var i =0; i< headerNames.length; i++)
      this.header.push(new BugListHeader(headerNames[i], "asc"));
  }
  createMap() {
    this.statusMap.set("new",["fixed"]);
    this.statusMap.set("fixed", ["open", "closed"]);
    this.statusMap.set("open", ["in_progress", "rejected"]);
    this.statusMap.set("rejected", ["closed"]);
    this.statusMap.set("in_progress", ["fixed", "rejected","info_needed"]);
    this.statusMap.set("info_needed", ["in_progress"]);
    this.statusMap.set("closed",[]);
    }


  getValuesForEntry(bug: Bug) {
      const finalList = this.statusMap.get(bug.status.toLocaleLowerCase());
      return finalList;
  }

  setSelectedBug(bug: Bug){
    this.filterDataService.filterList = [];
    this.filterDataService.filterList = Array.from(this.filters);
    this.dataService.bug = bug;
    localStorage.setItem("idBug", bug.idBug.toString());
  }

  sortColumn(column: BugListHeader){
    column.direction= column.direction === 'asc' ? 'desc' : 'asc';
    this.bugs = this.sortService.sortBugs(this.bugs,column);
  }

  filter(field: string, choice: string) {
    if (field !== "targetDate") {
      this.filters.push(new BugFilter(field, '', choice));
    } else {
      this.filters.push(new BugFilter(field, this.endDate, choice));
    }
  }

  validateDates():boolean{
    if((this.filterDataService.chosenFilter.targetDate && !this.endDate) || (!this.filterDataService.chosenFilter.targetDate && this.endDate) )
      return false;
    if(this.filterDataService.chosenFilter.targetDate > this.endDate)
      return false;
    return true;
  }

  validateFile(): boolean {
    if (this.bugs && this.bugs.length) {
      return true;
    }
    return false;
  }


  applyFilters() {
    this.filters = [];
    for (let [key, value] of Object.entries(this.filterDataService.chosenFilter)) {
      if (value) {
        this.filter(key.substr(1), value)
      }
    }
console.log(this.filterDataService.chosenFilter)
    console.log(this.filters)
    this.bugService.filterBugs(this.filters).subscribe(
      (response: Bug[]) => {
        this.bugs = [];
        response.forEach((value) => {
          this.isEditable[value.idBug] = false;
          this.bugs.push(value);
        });
      }
    );
  }

  loggedUser():User{
    return this.allUsers.filter((value) =>{
      return value.username === localStorage.getItem("currentUser");
    })[0];
  }

  updateBugUser(bug:Bug):User{
    return this.allUsers.filter((value) =>{
      return value.username === bug.assignedTo.username;
    })[0];
  }




  onSubmit(bug: Bug) {
    this.history.userDTO = this.loggedUser();
    this.history.bugDTO = bug;
    if (this.isEditable[bug.idBug]) {
      console.log('Bug updated');
      bug.status = bug.status.toUpperCase();
      bug.assignedTo = this.updateBugUser(bug);
      this.history.afterStatus = bug.status.toUpperCase();
      this.bugService.updateBug(bug);
      this.history.modifiedDate = new Date();
      if(this.history.beforeStatus !== this.history.afterStatus){
        this.bugService.addHistory(this.history).subscribe();
        this.history = new HistoryClass();
      }

      location.reload();
    }
    else {
      console.log('Bug ready to be updated');
      this.updatedStatus = this.getValuesForEntry(bug);
      this.updatedStatus.push(bug.status);
      this.history.beforeStatus = bug.status.toUpperCase();


    }
    this.isEditable[bug.idBug] = !this.isEditable[bug.idBug];
    console.log(bug.title);
    this.bugs.forEach((value) => {
      if (value.idBug !== bug.idBug)
        this.isEditable[value.idBug] = false;
    })


  }


  editableFunction(bug: Bug): boolean {
    return this.isEditable[bug.idBug];


  }

  exportAsXLSX(): void {
    var duplicateObject = JSON.parse(JSON.stringify( this.bugs ));
    console.log(duplicateObject);
    this.excelService.exportAsExcelFile(duplicateObject, 'bugs');
  }

}
