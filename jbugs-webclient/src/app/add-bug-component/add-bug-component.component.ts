import {Component, OnInit} from '@angular/core';
import {User} from "../types/user";
import {UserService} from "../services/user.service";
import {Attachment, AttachmentRest} from "../types/attachment";
import {Encoding} from "tslint/lib/utils";
import {BugService} from "../services/bug.service";
import {Bug, BugRest} from "../types/bugs";
import {BugAttachmentMapper} from "../types/mapper";

@Component({
  selector: 'app-add-bug-component',
  templateUrl: './add-bug-component.component.html',
  styleUrls: ['./add-bug-component.component.css']
})
export class AddBugComponentComponent implements OnInit {

  severity: string[] = ["CRITICAL","HIGH","MEDIUM","LOW"];
  chosenSeverity: string;
  chosenUsername: string;
  allUsers: User[] = [];
  attachment: Attachment = new AttachmentRest();
  readerResult: string;
  bug : Bug = new BugRest();
  mapper : BugAttachmentMapper;






  constructor(private userService:UserService,private bugService: BugService) {}

  changedSelected(chosenSeverity: string){
    console.log(chosenSeverity);
  }

  changedSelectedUsername(){
    console.log(this.chosenUsername);
}

  fileChange($event) {
    var reader: FileReader = new FileReader();
    //var readerResult: string;
    let eventTarget = <HTMLInputElement>event.target;
    if (eventTarget.files && eventTarget.files.length > 0) {
      let file = eventTarget.files[0];
      reader.onload = function () {
        this.attachment.blob = reader.result;
      }.bind(this);
      reader.readAsText(file);
      console.log("Outside" + this.readerResult);
    }

  }

  onSubmit(){
    this.mapper = new BugAttachmentMapper();
    this.bug.severity = this.chosenSeverity;
    this.bug.version = '1.0';
    this.bug.status = 'new';
    this.bug.targetDate = Date.now();
    this.bug.fixedVersion = null;
    this.bug.createdByUser = this.allUsers[0];
    this.bug.assignedTo = this.allUsers.filter(value =>{
      return value.userName === this.chosenUsername;
    })[0];
    this.attachment.bug = this.bug;
    this.mapper.bug = this.bug;
    this.mapper.attachment = this.attachment;


    console.log(this.mapper);
    this.bugService.addBug(this.mapper);
  }


  ngOnInit() {
    this.allUsers = this.userService.getAllUsers();

  }



}
