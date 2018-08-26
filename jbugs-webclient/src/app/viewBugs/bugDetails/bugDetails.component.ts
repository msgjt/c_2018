import {Component, Input, OnInit} from "@angular/core";
import {Bug, BugClass} from "../../types/bugs";
import {BugDataService} from "../../services/bugData.service";
import {BugService} from "../../services/bug.service";
import {Comment} from "../../types/comments";
import {Attachment} from "../../types/attachment";

@Component({
  selector: 'app-bugDetails',
  templateUrl: './bugDetails.component.html',
  styleUrls: ['./bugDetails.component.css', './../severity.css']
})

export class BugDetailsComponent implements OnInit{
  bug: Bug = new BugClass();
  comments: Comment[];
  attachmentChosen:Attachment;
  attachmentToBeAdded: Attachment;
  attachmentsForABug:Attachment[];

  constructor(public dataService: BugDataService, private bugService: BugService){
    this.attachmentToBeAdded = {
      bugDTO:null,
      blob: ""
    }



  }

  ngOnInit(): void {
      this.bugService.getBugById(Number(localStorage.getItem("idBug"))).subscribe((value:Bug)=>{
        if(value === undefined){
          console.log("undefined");
        }
        else{
          this.dataService.bug = value;
          this.setBugDetails();
        }

      });



  }

  setBugDetails(){
    console.log(JSON.stringify(this.bug));
    this.bug = this.dataService.bug;
    this.comments = this.bugService.getComments(this.bug.idBug);
    this.attachmentsForABug = this.bugService.getAllAttachmentsForABug(this.bug.idBug);
    console.log('Bugul ' + this.bug.idBug + ' are ' + this.attachmentsForABug.length + ' atasamente');
    this.attachmentToBeAdded.bugDTO = this.bug;
  }

  fileChange() {
    var reader: FileReader = new FileReader();
    let eventTarget = <HTMLInputElement>event.target;
    if (eventTarget.files && eventTarget.files.length > 0) {
      let file = eventTarget.files[0];
      console.log("file name"+ file.name);
      reader.onload = function () {
        this.attachmentToBeAdded.blob = reader.result;
      }.bind(this);
      reader.readAsText(file);
    }
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
    console.log(attachmentChosen);
    this.bugService.addAttachment(attachmentChosen);
    location.reload();
  }
}
