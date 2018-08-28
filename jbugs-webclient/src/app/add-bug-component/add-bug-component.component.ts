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
  chosenFiles: string[] = [];
  allUsers: User[] = [];
  attachment: Attachment[] = [];
  bug: Bug;
  chosenDate: string;
  currentDate: Date = new Date();



  constructor(private userService: UserService, private bugService: BugService) {
    this.bug = {
      idBug: 0,
      title: '',
      description: '',
      version: '1.0',
      targetDate: '',
      status: 'NEW',
      fixedVersion: '1.0',
      severity: '',
      createdByUser: null,
      assignedTo: null
    }
    // for(let i = 0 ; i<100;i++){
    //   this.attachment[i] = {
    //     bugDTO: null,
    //     blob: ""
    //   }
    // }
  }

  checkUndefined(value: string) {
    let type = typeof value;
    if (type === "undefined")
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
      for (let i = 0; i < eventTarget.files.length; i++) {
        let file = eventTarget.files[i];
        this.chosenFiles[i] = file.name;

        this.attachment[i] = {
          bugDTO: this.bug,
          blob: ""
        }
        reader.onload = function () {
          this.attachment[i].blob = reader.result;
        }.bind(this);
        reader.readAsText(file);
      }
    }

  }

  onSubmit() {
    this.bug.severity = this.chosenSeverity;
    this.bug.targetDate = this.chosenDate;
    this.bug.createdByUser = this.allUsers[0];
    this.bug.status = 'NEW';
    this.bug.assignedTo = this.allUsers.filter(value => {
      return value.username === this.chosenUsername;
    })[0];
    //this.bugService.addBug(this.bug);
    for (let i = 0; i < this.attachment.length; i++){
      this.attachment[i].bugDTO = this.bug;
      this.bugService.addAttachment(this.attachment[i]);
    }

    // location.reload();
  }


  ngOnInit() {
    this.allUsers = this.userService.getAllUsers();

  }


}
