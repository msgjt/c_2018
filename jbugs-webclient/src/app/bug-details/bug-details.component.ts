  import {Component, OnInit} from "@angular/core";
import {Bug} from "../types/bugs";
import {Attachment} from "../types/attachment";
import {BugDataService} from "../services/bugData.service";
import {Comment,CommentClass} from "../types/comments";
import {BugService} from "../services/bug.service";
import {UserService} from "../services/user.service";
import {ExportPDFService} from "../services/export-pdf.service";
import {AlertService} from "../services/alert.service";
import {HttpErrorResponse} from "@angular/common/http";
import {FilterService} from "../services/filter.service";


@Component({
  selector: 'app-bugDetails',
  templateUrl: './bug-details.component.html',
  styleUrls: ['./bug-details.component.css', './severity.css']
})

export class BugDetailsComponent implements OnInit {
  public bug: Bug;
  public comments: Comment[];
  public attachmentChosen: Attachment;
  public attachmentToBeAdded: Attachment;
  public attachmentsForABug: Attachment[] = [];
  public commentToBeAdded: Comment = new CommentClass();


  constructor(public dataService: BugDataService, private bugService: BugService, private userService: UserService, private bugPdfService: ExportPDFService, private alertService: AlertService,private filterService: FilterService){
    this.attachmentToBeAdded = {
      bugDTO: null,
      blob: new Uint8Array(),
      extension: '',
      name: ''
    }
    this.attachmentChosen = {
      bugDTO: null,
      blob: new Uint8Array(),
      extension: '',
      name: ''
    }

  }

  ngOnInit(): void {
    this.bugService.getBugById(Number(localStorage.getItem("idBug"))).subscribe((value: Bug) => {
      if (value === undefined) {

      }
      else {
        this.dataService.bug = value;
        this.setBugDetails();
      }
    }, (error: HttpErrorResponse) => {
      this.error("alerts." + error.error.toString());
    });


  }

  public setBugDetails():void {
    this.bug = this.dataService.bug;
    this.comments = this.bugService.getCommentsForABug(this.bug.idBug);
    this.attachmentsForABug = this.bugService.getAllAttachmentsForABug(this.bug.idBug);
    this.attachmentToBeAdded.bugDTO = this.bug;
  }

  public fileChange():void {
    var reader: FileReader = new FileReader();
    let eventTarget = <HTMLInputElement>event.target;
    if (eventTarget.files && eventTarget.files.length > 0) {
      let file = eventTarget.files[0];
      this.attachmentToBeAdded.extension = file.name.substring(file.name.length - 3).toUpperCase();
      this.attachmentToBeAdded.name = file.name.substring(0, file.name.length - 4);
      reader.onload = function () {
        this.attachmentToBeAdded.blob = reader.result;
      }.bind(this);
      reader.readAsArrayBuffer(file);
    }
  }

  public download(content, extension):void  {
    this.saveByteArray(this.attachmentChosen.name + "." + extension, content);
  }

  private saveByteArray(reportName, byte):void {
    var byteArray = new Uint8Array(byte);
    var blob = new Blob([byteArray], {type: "application/octet-stream"});
    var link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    var fileName = reportName;
    link.download = fileName;
    link.click();
  };

  public deleteAttachment(attachmentChosen: Attachment):void  {
    this.bugService.deleteAttachment(attachmentChosen).subscribe((response: Attachment) => {
      this.success("alerts.SUCCES-REMOVE");
    }, (error: HttpErrorResponse) => {
      this.error("alerts." + error.error.toString());
    });
    location.reload();
  }

  public addAttachment(attachmentChosen: Attachment):void  {
    let extensions: string[] = ["JPG", "DOC", "PDF", "ODF", "XLS", "TXT"];
    if (extensions.includes(attachmentChosen.extension.toUpperCase())) {
      this.bugService.sendFile(attachmentChosen.blob, attachmentChosen);
      this.success("alerts.SUCCES-ADD");
      location.reload();
    }
    else {
      this.error("alerts.FORMAT-FILE-ERROR");
    }
  }

  public addComment():void {
    this.commentToBeAdded.bugDTO = this.bug;
    this.commentToBeAdded.user = localStorage.getItem("currentUser");
    this.bugService.addComment(this.commentToBeAdded).subscribe((response:Comment) => {
      this.success("alerts.SUCCES-ADD");
      location.reload();
    }, (error: HttpErrorResponse) => {
      this.error("alerts.COMMENT_VALIDATION_EXCEPTION");
    });
  }

  public export():void  {
    this.bugPdfService.export(this.bug, this.comments, this.attachmentsForABug);
  }

  public success(message: string):void  {
    this.alertService.success(message);
  }

  public error(message: string):void  {
    this.alertService.error(message);
  }

}
