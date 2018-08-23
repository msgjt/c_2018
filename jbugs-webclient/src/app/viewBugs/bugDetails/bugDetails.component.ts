///<reference path="../../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, Input, OnInit} from "@angular/core";
import {Bug} from "../../types/bugs";
import {BugDataService} from "../../services/bugData.service";

@Component({
  selector: 'app-bugDetails',
  templateUrl: './bugDetails.component.html',
  styleUrls: ['./bugDetails.component.css', './../viewBugs.component.css']
})

export class BugDetailsComponent implements OnInit{
  bug: Bug;

  constructor(public dataService: BugDataService){}

  ngOnInit(): void {
    this.bug = this.dataService.bug;
  }


}
