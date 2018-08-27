import {Component, OnInit} from '@angular/core';
import {Bug} from "../types/bugs";
import {BugService} from "../services/bug.service";
import {Attachment} from "../types/attachment";
import {User} from "../types/user";
import {UserService} from "../services/user.service";
import {BugDataService} from "../services/bugData.service";

@Component({
  selector: 'app-update-bug',
  templateUrl: './update-bug.component.html',
  styleUrls: ['./update-bug.component.css']
})
export class UpdateBugComponent implements OnInit {

  page: number=1;
  bugs: Bug[];
  isEditable: boolean[] = [];
  cachedBugs: Bug[];
  allUsers: User[] = [];
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


  constructor(private bugService: BugService,private userService: UserService,private dataService:BugDataService) {
    this.attachmentToBeAdded = {
      bugDTO:null,
      blob: ""
    }
  }

  ngOnInit(): void {
    this.bugs = this.bugService.getAll();
    this.cachedBugs = this.bugs;
    this.bugs.forEach((value) => {
      this.isEditable[value.idBug] = false;
    })
    this.createMap();
    this.attachments = this.bugService.getAllAttachments();
    this.userService.getUsers().subscribe((response:User[])=>{
        response.forEach((value => {
          this.allUsers.push(value);
        }))
    })
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
    this.dataService.bug = bug;
    localStorage.setItem("idBug",bug.idBug.toString());
  }

  filterBySeverity() {
    if (this.chosenSeverity == "undefined") {
      this.bugs = this.cachedBugs;
    }
    else {
      this.bugs = this.bugs.filter((item) => item.severity == this.chosenSeverity.toUpperCase());
    }
  }


  filterByStatus() {
    if (this.chosenStatus == "undefined") {
      this.bugs = this.cachedBugs;
    }
    else {
      this.bugs = this.bugs.filter((item) => item.status == this.chosenStatus.toUpperCase());
    }
  }

  updateBugUser(bug:Bug):User{
    return this.allUsers.filter((value) =>{
      return value.username === bug.assignedTo.username;
    })[0];
  }



  onSubmit(bug: Bug) {

    if (this.isEditable[bug.idBug]) {
      console.log('Bug updated');
      bug.status = bug.status.toUpperCase();
      bug.assignedTo = this.updateBugUser(bug);
      this.bugService.updateBug(bug);
      location.reload();
    }
    else {
      console.log('Bug ready to be updated');
      this.updatedStatus = this.getValuesForEntry(bug);
      this.updatedStatus.push(bug.status);

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




}
