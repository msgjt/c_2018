import {Component, OnInit} from '@angular/core';
import {Bug} from "../types/bugs";
import {BugService} from "../services/bug.service";
import {Attachment} from "../types/attachment";

@Component({
  selector: 'app-update-bug',
  templateUrl: './update-bug.component.html',
  styleUrls: ['./update-bug.component.css']
})
export class UpdateBugComponent implements OnInit {

  bugs: Bug[];
  isEditable: boolean[] = [];
  cachedBugs: Bug[];
  severities: string[] = ["critical", "high", "medium", "low"];
  chosenSeverity: string;
  statuses: string[] = ["fixed", "open", "in_progress", "rejected", "info_needed", "closed"];
  chosenStatus: string;
  bug: Bug;
  statusMap = new Map();
  updatedStatus : string[];
  attachments: Attachment[];
  attachmentsForABug: Attachment[];
  attachmentChosen:Attachment;


  constructor(private bugService: BugService) {
  }

  ngOnInit(): void {
    this.bugs = this.bugService.getAll();
    this.cachedBugs = this.bugs;
    this.bugs.forEach((value) => {
      this.isEditable[value.idBug] = false;
    })
    this.createMap();
    this.attachments = this.bugService.getAllAttachments();
  }

  createMap() {
    this.statusMap.set("fixed", ["open", "closed"]);
    this.statusMap.set("open", ["in_progress", "rejected"]);
    this.statusMap.set("rejected", ["closed"]);
    this.statusMap.set("in_progress", ["fixed", "rejected","info_needed"]);
    this.statusMap.set("info_needed", ["in_progress"]);
    }


  getValuesForEntry(bug: Bug) {
      const finalList = this.statusMap.get(bug.status.toLocaleLowerCase());
      return finalList;
  }

  filterBySeverity() {
    if (this.chosenSeverity == "undefined") {
      this.bugs = this.cachedBugs;
    }
    else {
      this.bugs = this.bugs.filter((item) => item.severity == this.chosenSeverity.toUpperCase());
    }
  }


  filterByStatus() {
    if (this.chosenStatus == "undefined") {
      this.bugs = this.cachedBugs;
    }
    else {
      this.bugs = this.bugs.filter((item) => item.status == this.chosenStatus.toUpperCase());
    }
  }


  onSubmit(bug: Bug) {

    if (this.isEditable[bug.idBug]) {
      console.log('Bug updated');
      bug.status = bug.status.toUpperCase();
      this.bugService.updateBug(bug);
    }
    else {
      console.log('Bug ready to be updated');
      this.updatedStatus = this.getValuesForEntry(bug);
      this.updatedStatus.push(bug.status);

    }
    this.isEditable[bug.idBug] = !this.isEditable[bug.idBug];
    console.log(bug.title);
    this.bugs.forEach((value) => {
      if (value.idBug !== bug.idBug)
        this.isEditable[value.idBug] = false;
    })


  }

  loadAttachments(bug:Bug){
    this.attachmentsForABug = this.attachments.filter((value) =>{
      return value.bugDTO.idBug == bug.idBug;
    });
    console.log('Bugul ' + bug.idBug + ' are ' + this.attachmentsForABug.length + ' atasamente');
  }

  editableFunction(bug: Bug): boolean {
    return this.isEditable[bug.idBug];


  }

  download(content) {
    var filename= 'from UI';
    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(content));
    element.setAttribute('download', filename);

    element.style.display = 'none';
    document.body.appendChild(element);

    element.click();

    document.body.removeChild(element);
  }

  delete(attachmentChosen:Attachment){
    this.bugService.deleteAttachment(attachmentChosen);
  }


}
