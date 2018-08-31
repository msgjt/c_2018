import {Component, OnInit} from '@angular/core';
import {User} from "../types/user";
import {UserService} from "../services/user.service";
import {Attachment} from "../types/attachment";
import {BugService} from "../services/bug.service";
import {Bug} from "../types/bugs";
import {$} from "jQuery";
import {AlertService} from "../services/alert.service";
import {HttpErrorResponse} from "@angular/common/http";


@Component({
  selector: 'app-add-bug-component',
  templateUrl: './add-bug.component.html',
  styleUrls: ['./add-bug.component.css']
})
export class AddBugComponentComponent implements OnInit {

  public severity: string[] = ["CRITICAL", "HIGH", "MEDIUM", "LOW"];
  public chosenSeverity: string;
  public chosenUsername: string;
  public chosenFiles: string[] = [];
  public allUsers: User[] = [];
  public attachment: Attachment[] = [];
  public bug: Bug;
  public chosenDate: string;
  public currentDate: Date = new Date();
  public extensions: string[] = ["JPG", "DOC", "PDF", "ODF", "XLS", "TXT"];


  constructor(private userService: UserService, private bugService: BugService, private alertService: AlertService) {
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


  /**
   * Method used for uploading and reading the selected files
   */
  public fileChange():void {
    let reader: FileReader[] = [];
    let eventTarget = <HTMLInputElement>event.target;
    if (eventTarget.files && eventTarget.files.length > 0) {
      for (let i = 0; i < eventTarget.files.length; i++) {
        let file = eventTarget.files[i];
        reader[i] = new FileReader();
        this.chosenFiles[i] = file.name;
        this.attachment[i] = {
          bugDTO: this.bug,
          blob: new Uint8Array(),
          extension: file.name.substring(file.name.length - 3).toUpperCase(),
          name: file.name.substring(0, file.name.length - 4)
        };
        reader[i].onload = (e) => {
          this.attachment[i].blob = reader[i].result;
        };
        reader[i].readAsArrayBuffer(file);
      }
    }

  }

  /**
   * Method used for creating the bug and its attachments
   */
  public onSubmit():void {
    this.bug.severity = this.chosenSeverity;
    this.bug.targetDate = this.chosenDate;
    this.bug.createdByUser = this.allUsers[0];
    this.bug.status = 'NEW';
    this.bug.assignedTo = this.allUsers.filter(value => {
      return value.username === this.chosenUsername;
    })[0];
    console.log('Se adauga ' + this.attachment.length + ' atasamente')
    this.bugService.addBug(this.bug).subscribe((value) => {
      for (let i = 0; i < this.attachment.length; i++) {
        if (this.extensions.includes(this.attachment[i].extension)) {
          this.attachment[i].bugDTO = this.bug;
          this.bugService.sendFile(this.attachment[i].blob, this.attachment[i]);

        }
        else {
          this.error("alerts.FORMAT-FILE-ERROR");
          console.log("Format invalid");
        }
      }
      this.success("alerts.SUCCES-ADD");
      this.attachment = [];
      this.chosenFiles = [];
      location.reload();
    }, (error: HttpErrorResponse) => {
      this.error('alerts.' + error.error.toString());
    });
  }


  ngOnInit() {
    this.allUsers = this.userService.getAllUsers();

  }

  public success(message: string):void {
    this.alertService.success(message);
  }

  public error(message: string):void {
    this.alertService.error(message);
  }


}
