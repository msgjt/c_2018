import {Component, OnInit} from "@angular/core";
import {Bug, BugClass} from "../../types/bugs";
import {BugDataService} from "../../services/bugData.service";
import {BugService} from "../../services/bug.service";
import {Comment, CommentClass} from "../../types/comments";
import {Attachment} from "../../types/attachment";
import {UserService} from "../../services/user.service";
import {ExportPDFService} from "../../services/export-pdf.service";

@Component({
  selector: 'app-bugDetails',
  templateUrl: './bugDetails.component.html',
  styleUrls: ['./bugDetails.component.css', './../severity.css']
})

export class BugDetailsComponent implements OnInit {
  bug: Bug;
  comments: Comment[];
  attachmentChosen: Attachment;
  attachmentToBeAdded: Attachment;
  attachmentsForABug: Attachment[] = [];
  commentToBeAdded: Comment = new CommentClass();

  constructor(public dataService: BugDataService, private bugService: BugService, private userService: UserService, private bugPdfService: ExportPDFService) {
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
        console.log("undefined");
      }
      else {
        this.dataService.bug = value;
        this.setBugDetails();
      }
    });


  }

  setBugDetails() {
    this.bug = this.dataService.bug;
    this.comments = this.bugService.getComments(this.bug.idBug);
    this.attachmentsForABug = this.bugService.getAllAttachmentsForABug(this.bug.idBug);
    this.attachmentToBeAdded.bugDTO = this.bug;
  }

  fileChange() {
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

  download(content, extension) {
    this.saveByteArray("copieeed." + extension, content, extension);
  }

  saveByteArray(reportName, byte, extension) {
    var byteArray = new Uint8Array(byte);
    console.log(byte);
    console.log(byteArray);
    var blob = new Blob([byteArray], {type: "application/octet-stream"});
    var link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    var fileName = reportName;
    link.download = fileName;
    link.click();
  };

  deleteAttachment(attachmentChosen: Attachment) {
    this.bugService.deleteAttachment(attachmentChosen);
    location.reload();
  }

  addAttachment(attachmentChosen: Attachment) {
    let extensions: string[] = ["JPG", "DOC", "PDF", "ODF", "XLS", "TXT"];

    if (extensions.includes(this.attachmentChosen.extension)) {
      this.bugService.sendFile(attachmentChosen.blob, attachmentChosen);
      location.reload();
    }
  }

  addComment() {
    this.commentToBeAdded.bugDTO = this.bug;
    this.commentToBeAdded.user = "doreld";
    this.bugService.addComment(this.commentToBeAdded);
  }

  export() {
    this.bugPdfService.export(this.bug, this.comments, this.attachmentsForABug);
  }
}
