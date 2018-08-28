import { Injectable } from '@angular/core';
import * as jsPDF from 'jspdf'
import {Bug} from "../types/bugs";
import {Comment, CommentClass} from "..//types/comments";

@Injectable({
  providedIn: 'root'
})
export class ExportPDFService {

  constructor() { }
  export(bug:Bug,comments:Comment[]){
    var doc = new jsPDF();
    let row=180;
    doc.text("JBugger raport of bug #"+bug.idBug,70,20);
    doc.text("Title: "+bug.title,30,40);
    doc.text("Description: "+bug.description,30,50);
    doc.text("Revision/Version: "+bug.version,30,60);
    doc.text("Fixed in version/Revision: "+bug.version,30,70);
    doc.text("Target date: "+bug.targetDate,30,80);
    doc.text("Severity: "+bug.severity,30,90);
    doc.text("Created by: "+bug.createdByUser.firstName+" "+bug.createdByUser.lastName,30,100);
    doc.text("Status: "+bug.status,30,110);
    doc.text("Assigned to: "+bug.assignedTo.firstName+" "+bug.assignedTo.lastName,30,120);
    doc.text("Attachments "+bug,30,130);
    doc.text("Comments",85,160);
    for(let comment of comments){
      doc.text(comment.text,30,row);
      row=row+10;
    }



    // Save the PDF
    doc.save('Test.pdf');
  }
}
