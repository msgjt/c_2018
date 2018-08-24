///<reference path="../../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, Input, OnInit} from "@angular/core";
import {Bug} from "../../types/bugs";
import {BugDataService} from "../../services/bugData.service";
import {BugService} from "../../services/bug.service";
import {Comment} from "../../types/comments";

@Component({
  selector: 'app-bugDetails',
  templateUrl: './bugDetails.component.html',
  styleUrls: ['./bugDetails.component.css', './../severity.css']
})

export class BugDetailsComponent implements OnInit{
  bug: Bug;
  comments: Comment[];

  constructor(public dataService: BugDataService, private bugService: BugService){}

  ngOnInit(): void {
    this.bug = this.dataService.bug;
    this.comments = this.bugService.getComments(this.bug.idBug);
  }

  //ToDo display attachments
}
