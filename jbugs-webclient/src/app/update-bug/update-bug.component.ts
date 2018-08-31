import {Component, OnInit} from '@angular/core';
import {Bug} from "../types/bugs";
import {BugService} from "../services/bug.service";
import {Attachment} from "../types/attachment";
import {User} from "../types/user";
import {UserService} from "../services/user.service";
import {BugDataService} from "../services/bugData.service";
import {BugFilter} from "../types/bug-filter";
import {BugListHeader} from "../types/bug-list-header";
import {BugSortService} from "../services/bug-sort.service";
import {FilterDataService} from "../services/filter-data.service";
import {ExcelService} from "../services/excel.service";
import {AlertService} from "../services/alert.service";
import {HttpErrorResponse} from "@angular/common/http";
import {HistoryClass} from "../types/history";
import {FilterService} from "../services/filter.service";

@Component({
  selector: 'app-update-bug',
  templateUrl: './update-bug.component.html',
  styleUrls: ['./update-bug.component.css']
})
export class UpdateBugComponent implements OnInit {
  public page: number = 1;
  public bugs: Bug[] = [];
  public isEditable: boolean[] = [];
  public allUsers: User[] = [];
  public endDate: string;
  public severity: string[] = ["CRITICAL", "HIGH", "MEDIUM", "LOW"];
  public chosenSeverity: string;
  public statuses: string[] = ["fixed", "open", "in_progress", "rejected", "info_needed", "closed"];
  public bug: Bug;
  public statusMap = new Map();
  public updatedStatus : string[];
  public attachments: Attachment[];
  public attachmentsForABug: Attachment[];
  public attachmentChosen:Attachment;
  public attachmentToBeAdded: Attachment;
  public filters: BugFilter[] = [];
  public header: BugListHeader[] = [];
  public history: HistoryClass = new HistoryClass();
  public validationModel:boolean = true;

  constructor(public filterDataService: FilterDataService, private bugService: BugService, private userService: UserService, private dataService: BugDataService, private sortService: BugSortService, private excelService: ExcelService,private alertService: AlertService, private filterService: FilterService) {
    this.attachmentToBeAdded = {
      bugDTO:null,
      blob: null,
      extension:'',
      name:''
    }
  }

  ngOnInit(){
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

  public createHeader():void{
    var headerNames = ["title","version", "fixed version", "severity", "status", "assigned to", "target date", "created by"];
    for(var i =0; i< headerNames.length; i++)
      this.header.push(new BugListHeader(headerNames[i], "asc"));
  }
  public createMap():void {
    this.statusMap.set("new",["fixed"]);
    this.statusMap.set("fixed", ["open", "closed"]);
    this.statusMap.set("open", ["in_progress", "rejected"]);
    this.statusMap.set("rejected", ["closed"]);
    this.statusMap.set("in_progress", ["fixed", "rejected","info_needed"]);
    this.statusMap.set("info_needed", ["in_progress"]);
    this.statusMap.set("closed",[]);
    }


  public getValuesForEntry(bug: Bug):string[] {
      const finalList = this.statusMap.get(bug.status.toLocaleLowerCase());
      return finalList;
  }

  public setSelectedBug(bug: Bug):void{
    this.filterDataService.filterList = [];
    this.filterDataService.filterList = Array.from(this.filters);
    this.dataService.bug = bug;
    localStorage.setItem("idBug", bug.idBug.toString());
  }

  public sortColumn(column: BugListHeader):void{
    column.direction= column.direction === 'asc' ? 'desc' : 'asc';
    this.bugs = this.sortService.sortBugs(this.bugs,column);
  }

  public filter(field: string, choice: string):void {
    if (field !== "targetDate") {
      this.filters.push(new BugFilter(field, '', choice));
    } else {
      this.filters.push(new BugFilter(field, this.endDate, choice));
    }
  }

  public validateDates():boolean{
    if((this.filterDataService.chosenFilter.targetDate && !this.endDate) || (!this.filterDataService.chosenFilter.targetDate && this.endDate) )
      return false;
    if(this.filterDataService.chosenFilter.targetDate > this.endDate)
      return false;
    return true;
  }

  public validateFile(): boolean {
    if (this.bugs && this.bugs.length) {
      return true;
    }
    return false;
  }


  public applyFilters():void {
    this.filters = [];
    for (let [key, value] of Object.entries(this.filterDataService.chosenFilter)) {
      if (value) {
        this.filter(key.substr(1), value);
      }
    }
    console.log(this.filterDataService.chosenFilter);
    console.log(this.filters);
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

  public loggedUser():User{
    return this.allUsers.filter((value) =>{
      return value.username === localStorage.getItem("currentUser");
    })[0];
  }

  public updateBugUser(bug:Bug):User{
    return this.allUsers.filter((value) =>{
      return value.username === bug.assignedTo.username;
    })[0];
  }


  public onSubmit(bug: Bug):void {
    this.history.userDTO = this.loggedUser();
    this.history.bugDTO = bug;
    // if(!this.validationModel){
    //   this.error("Invalid format");
    //   location.reload();
    // }
    if (this.isEditable[bug.idBug] && this.validationModel) {
      console.log('Bug updated');
      bug.status = bug.status.toUpperCase();
      bug.assignedTo = this.updateBugUser(bug);
      this.history.afterStatus = bug.status.toUpperCase();
      this.bugService.updateBug(bug).subscribe((response: Bug) => {
        this.success("alerts.SUCCES-UPDATE");
      }, (error: HttpErrorResponse) => {
        this.error("alerts." + error.error.toString());
        console.log('user errors');
      });

      this.history.modifiedDate = new Date();
      if (this.history.beforeStatus !== this.history.afterStatus) {
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

  public validation(bool:boolean){
    this.validationModel = bool;
  }

  public editableFunction(bug: Bug): boolean {
    return this.isEditable[bug.idBug];
  }


  public exportAsXLSX(): void {
    var duplicateObject = JSON.parse(JSON.stringify( this.bugs ));
    console.log(duplicateObject);
    this.excelService.exportAsExcelFile(duplicateObject, 'bugs');
  }

  public success(message: string):void {
    this.alertService.success(message);
  }

  public error(message: string):void {
    this.alertService.error(message);
  }
}
