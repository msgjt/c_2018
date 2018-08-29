import {Injectable} from '@angular/core';
import * as jsPDF from 'jspdf'
import {Bug} from "../types/bugs";
import {Comment, CommentClass} from "..//types/comments";
import {Attachment} from "../types/attachment";
import {el} from "@angular/platform-browser/testing/src/browser_util";

@Injectable({
  providedIn: 'root'
})
export class ExportPDFService {

  constructor() {
  }

  export(bug: Bug, comments: Comment[], attachments: Attachment[]) {
    var doc = new jsPDF();
    let row = 180;
    let namesAttachments = "";
    if (attachments.length>0) {
      attachments.forEach(value => namesAttachments = namesAttachments + " " + value.extension);
    } else
      namesAttachments = "none";
    doc.text("JBugger raport of bug #" + bug.idBug, 70, 20);
    doc.text("Title: " + bug.title, 30, 40);
    doc.text("Description: " + bug.description, 30, 50);
    doc.text("Revision/Version: " + bug.version, 30, 60);
    doc.text("Fixed in version/Revision: " + bug.fixedVersion, 30, 70);
    doc.text("Target date: " + bug.targetDate, 30, 80);
    doc.text("Severity: " + bug.severity, 30, 90);
    doc.text("Created by: " + bug.createdByUser.username, 30, 100);
    doc.text("Status: " + bug.status, 30, 110);
    doc.text("Assigned to: " + bug.assignedTo.username, 30, 120);
    doc.text("Attachments: "+namesAttachments, 30, 130);
    doc.text("Comments", 85, 160);
    for (let comment of comments) {
      doc.text("#" + comment.id, 30, row)
      doc.text(comment.date + " " + comment.user + ": " + comment.text, 30, row + 8);
      row = row + 18;
    }


    // Save the PDF
    doc.save('Test.pdf');
  }
}
