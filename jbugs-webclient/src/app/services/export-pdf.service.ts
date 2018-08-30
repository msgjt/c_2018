import {Injectable} from '@angular/core';
import * as jsPDF from 'jspdf'
import {Bug} from "../types/bugs";
import {Comment, CommentClass} from "..//types/comments";
import {Attachment} from "../types/attachment";

@Injectable({
  providedIn: 'root'
})
export class ExportPDFService {
  column = 30;
  row = 10;
  doc = new jsPDF();

  constructor() {
  }

  export(bug: Bug, comments: Comment[], attachments: Attachment[]) {

    let namesAttachments = "";
    if (attachments.length > 0) {
      attachments.forEach(value => namesAttachments = namesAttachments + " " + value.name);
    } else
      namesAttachments = "none";
    this.doc.text("JBugger raport of bug #" + bug.idBug, this.column + 40, this.incrementRow(10));
    this.doc.text("Title: " + bug.title, this.column, this.incrementRow(10));
    this.doc.text("Revision/Version: " + bug.version, this.column, this.incrementRow(10));
    this.doc.text("Fixed in version/Revision: " + bug.fixedVersion, this.column, this.incrementRow(20));
    this.doc.text("Target date: " + bug.targetDate, this.column, this.incrementRow(20));
    this.doc.text("Severity: " + bug.severity, this.column, this.incrementRow(20));
    this.doc.text("Created by: " + bug.createdByUser.username, this.column, this.incrementRow(20));
    this.doc.text("Status: " + bug.status, this.column, this.incrementRow(20));
    this.doc.text("Assigned to: " + bug.assignedTo.username, this.column, this.incrementRow(20));
    this.doc.text("Attachments: " + namesAttachments, this.column, this.incrementRow(20));
    this.doc.text("Description: ", this.column, this.incrementRow(20));
    this.addTextOnPage(bug.description);
    this.doc.text("---Comments---", this.column + 55, this.incrementRow(20)); //85.160
    for (let comment of comments) {
      this.addTextOnPage("#" + comment.id + " " + comment.date + " " + comment.user + " - " + comment.text);
    }
    this.doc.save('Bug#' + bug.idBug + '.pdf');
  }

  incrementRow(value: number) {
    this.row = this.row + value;
    return this.row;
  }

  addTextOnPage(value: string) {
    let pageHeight = this.doc.internal.pageSize.height = 257;
    if (this.row >= pageHeight) {
      this.doc.addPage();
      this.row = 0 // Restart height position
    }
    var lines = this.doc.splitTextToSize(value, (150));
    this.doc.text(this.column, this.incrementRow(20), lines);
  }


}
