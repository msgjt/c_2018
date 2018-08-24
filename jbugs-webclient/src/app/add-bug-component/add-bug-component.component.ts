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
  bug: Bug;
  chosenDate: string;


  constructor(private userService: UserService, private bugService: BugService) {
    this.bug = {
      idBug: 0,
      title: '',
      description: '',
      version: '',
      targetDate: '',
      status: '',
      fixedVersion: '',
      severity: '',
      createdByUser: null,
      assignedTo: null
    }
    this.attachment = {
      bugDTO:null,
      blob:""
    }
  }

  checkUndefined(value: string){
    let type = typeof value;
    if(type === "undefined")
      return false;
    return true;
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
    }
  }

  onSubmit() {
    this.bug.severity = this.chosenSeverity;
    this.bug.version = '1.0';
    this.bug.status = 'OPEN';
    this.bug.targetDate = this.chosenDate;
    this.bug.fixedVersion = '1.0';
    this.bug.createdByUser = this.allUsers[0];
    this.bug.assignedTo = this.allUsers.filter(value => {
      return value.username === this.chosenUsername;
    })[0];
    this.attachment.bugDTO = this.bug;
    this.bugService.addBug(this.attachment);
  }


  ngOnInit() {
    this.allUsers = this.userService.getAllUsers();

  }


}
