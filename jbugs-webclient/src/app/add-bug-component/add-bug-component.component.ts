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
  extensions: Array<string> = ["JPG","DOC","PDF","ODF","XLS"];



  constructor(private userService: UserService, private bugService: BugService) {
    this.bug = {
      idBug: 0,
      title: '',
      description: '',
      version: '',
      targetDate: '',
      status: 'NEW',
      fixedVersion: '',
      severity: '',
      createdByUser: null,
      assignedTo: null
    }
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
    let reader : FileReader[] = [];
    let eventTarget = <HTMLInputElement>event.target;
    if (eventTarget.files && eventTarget.files.length > 0) {
      for (let i = 0; i < eventTarget.files.length; i++) {
        let file = eventTarget.files[i];
        reader[i] = new FileReader();
        this.chosenFiles[i] = file.name;
        this.attachment[i] = {
          bugDTO: this.bug,
          blob: new Uint8Array(),
          extension:file.name.substring(file.name.length - 3).toUpperCase()
        }
        reader[i].onload = (e) =>{
          this.attachment[i].blob = reader[i].result;
        }
        reader[i].readAsArrayBuffer(file);
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
    console.log('Se adauga ' + this.attachment.length + ' atasamente')
    this.bugService.addBug(this.bug).subscribe((value) =>{
      for (let i = 0; i < this.attachment.length; i++){
        if(this.extensions.includes(this.attachment[i].extension)){
          this.attachment[i].bugDTO = this.bug;
          this.bugService.sendFile(this.attachment[i].blob,this.attachment[i]);
        }
        else{
          console.log("Format invalid");
        }

      }
      this.attachment =[];
      this.chosenFiles=[];
    });

  }


  ngOnInit() {
    this.allUsers = this.userService.getAllUsers();

  }


}
