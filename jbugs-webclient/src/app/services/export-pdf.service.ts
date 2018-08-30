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
    var numberComments = 0;
    let row = 60;

    var lMargin=15; //left margin in mm
    var rMargin=15; //right margin in mm
    var pdfInMM=210;  // width of A4 in mm
    var h=doc.internal.pageSize.height=297;
    let namesAttachments = "";
    if (attachments.length > 0) {
      attachments.forEach(value => namesAttachments = namesAttachments + " " + value.name);
    } else
      namesAttachments = "none";
    doc.setPage(1);

    doc.text("JBugger raport of bug #" + bug.idBug, 70, 20);
    doc.text("Title: " + bug.title, 30, 40);
    doc.text("Revision/Version: " + bug.version, 30, 50);
    doc.text("Fixed in version/Revision: " + bug.fixedVersion, 30, 60);
    doc.text("Target date: " + bug.targetDate, 30, 70);
    doc.text("Severity: " + bug.severity, 30, 80);
    doc.text("Created by: " + bug.createdByUser.username, 30, 90);
    doc.text("Status: " + bug.status, 30, 100);
    doc.text("Assigned to: " + bug.assignedTo.username, 30, 110);
    doc.text("Attachments: " + namesAttachments, 30, 120);
    var description = bug.description;
    var lines = doc.splitTextToSize(description, (pdfInMM-lMargin-rMargin));
    doc.text("Description: ", 30, 130);
    doc.text(30,140, lines);
    doc.addPage();
    doc.setPage(2);
    doc.text("Comments", 30, 40); //85.160
    for (let comment of comments) {
      console.log("nr comm: "+numberComments);
      doc.text("#" + comment.id, 30, row)
      doc.text(comment.date , 30, row + 8);
      var comm = comment.text;
      var lines2 = doc.splitTextToSize(comm,(pdfInMM-lMargin-rMargin));
      doc.text(comment.user,30,row+16);
      doc.text(lines2,30,row+32);
      row = row + 40;
    }


    // Save the PDF
    doc.save('Bug#' + bug.idBug + '.pdf');
  }


}
