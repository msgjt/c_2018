import {Component, OnInit} from '@angular/core';
import {Bug} from "../types/bugs";
import {BugService} from "../services/bug.service";
import {Attachment} from "../types/attachment";
import {User} from "../types/user";
import {UserService} from "../services/user.service";

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


  constructor(private bugService: BugService,private userService: UserService) {
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
    this.statusMap.set("fixed", ["open", "closed"]);
    this.statusMap.set("open", ["in_progress", "rejected"]);
    this.statusMap.set("rejected", ["closed"]);
    this.statusMap.set("in_progress", ["fixed", "rejected","info_needed"]);
    this.statusMap.set("info_needed", ["in_progress"]);
    }


  getValuesForEntry(bug: Bug) {
      const finalList = this.statusMap.get(bug.status.toLocaleLowerCase());
      return finalList;
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

  fileChange($event) {
    this.attachmentToBeAdded = null;
    var reader: FileReader = new FileReader();
    let eventTarget = <HTMLInputElement>event.target;
    if (eventTarget.files && eventTarget.files.length > 0) {
      let file = eventTarget.files[0];
      reader.onload = function () {
        this.attachmentToBeAdded.blob = reader.result;
        console.log(this.attachmentToBeAdded.blob)
      }.bind(this);
      reader.readAsText(file);
    }
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

  clickDetails(bug:Bug){
    this.attachmentsForABug = this.attachments.filter((value) =>{
      return value.bugDTO.idBug == bug.idBug;
    });
    console.log('Bugul ' + bug.idBug + ' are ' + this.attachmentsForABug.length + ' atasamente');
    this.attachmentToBeAdded.bugDTO = bug;
  }

  editableFunction(bug: Bug): boolean {
    return this.isEditable[bug.idBug];


  }

  download(content) {
    var filename= 'Attachment';
    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(content));
    element.setAttribute('download', filename);

    element.style.display = 'none';
    document.body.appendChild(element);

    element.click();

    document.body.removeChild(element);
  }

  deleteAttachment(attachmentChosen:Attachment){
    this.bugService.deleteAttachment(attachmentChosen);
    location.reload();
  }

  addAttachment(attachmentChosen:Attachment){
    this.bugService.addAttachment(attachmentChosen);
  }


}
