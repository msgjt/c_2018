import {Component, OnInit} from '@angular/core';
import {User} from "../types/user";
import {UserService} from "../services/user.service";
import {Attachment} from "../types/attachment";
import {BugService} from "../services/bug.service";
import {Bug} from "../types/bugs";
import {$} from "jQuery";


@Component({
  selector: 'app-add-bug-component',
  templateUrl: './add-bug-component.component.html',
  styleUrls: ['./add-bug-component.component.css']
})
export class AddBugComponentComponent implements OnInit {

  severity: string[] = ["CRITICAL", "HIGH", "MEDIUM", "LOW"];
  chosenSeverity: string;
  chosenUsername: string;
  allUsers: User[] = [];
  attachment: Attachment;
  readerResult: string;
  bug: Bug;


  constructor(private userService: UserService, private bugService: BugService) {
    this.bug = {
      idBug: 0,
      title: '',
      description: '',
      version: '',
      targetDate: 0,
      status: '',
      fixedVersion: '2.0',
      severity: '',
      createdByUser: null,
      assignedTo: null
    }
    this.attachment = {
      bugDTO:null,
      blob:""
    }
  }

  changedSelected(chosenSeverity: string) {
    console.log(chosenSeverity);
  }

  changedSelectedUsername() {
    console.log(this.chosenUsername);
  }

  fileChange($event) {
    var reader: FileReader = new FileReader();
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

  onSubmit() {
    this.bug.severity = this.chosenSeverity;
    this.bug.version = '1.0';
    this.bug.status = 'OPEN';
    this.bug.targetDate = Date.now();
    this.bug.fixedVersion = '2.0';
    this.bug.createdByUser = this.allUsers[0];
    this.bug.assignedTo = this.allUsers.filter(value => {
      return value.userName === this.chosenUsername;
    })[0];
    this.attachment.bugDTO = this.bug;
    this.bugService.addBug(this.attachment);
  }


  ngOnInit() {
    this.allUsers = this.userService.getAllUsers();

  }


}
