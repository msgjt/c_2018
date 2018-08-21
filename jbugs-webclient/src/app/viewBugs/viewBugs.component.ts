///<reference path="../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, OnInit} from "@angular/core";
import {BugService} from "../services/bug.service";
import {Bug} from "../types/bugs";

@Component({
  selector: 'app-bug',
  templateUrl: './viewBugs.component.html',
  styleUrls: ['./viewBugs.component.css']
})

export class ViewBugsComponent implements OnInit{
  bugs: Bug[];
  cachedBugs: Bug[];
  severities:string[] = ["critical", "high", "medium", "low"];
  chosenSeverity:string;

  constructor(private bugService: BugService){
  }

  ngOnInit(): void {
    this.bugs = this.bugService.getAll();
    this.cachedBugs = this.bugs;
  }

  filterBySeverity(){
    if(this.chosenSeverity == "undefined"){
      this.bugs = this.cachedBugs;
    }
    else{
      this.bugs = this.bugService.filterBySeverity(this.chosenSeverity)
    }
  }
}
