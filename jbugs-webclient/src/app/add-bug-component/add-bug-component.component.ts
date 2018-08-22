import {Component, OnInit} from '@angular/core';
import {User} from "../types/user";
import {UserService} from "../services/user.service";
import {Attachment, AttachmentRest} from "../types/attachment";
import {Encoding} from "tslint/lib/utils";
import {BugService} from "../services/bug.service";
import {Bug} from "../types/bugs";

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
  bugs: Bug[];






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
    //this.attachment.blob = "SEICUL";
    console.log(this.bugs.length);
    this.attachment.bug = this.bugs.pop();
    console.log(this.attachment.bug);
    this.bugService.testAttachment(this.attachment);
  }


  ngOnInit() {
    this.allUsers = this.userService.getAllUsers();
    this.bugs=this.bugService.getAll();
  }



}
