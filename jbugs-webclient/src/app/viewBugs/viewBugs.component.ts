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
  statuses:string[] = ["fixed", "open", "in_progress", "rejected", "info_nedded", "closed"];
  chosenStatus:string;

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
      this.bugs = this.bugs.filter((item)=> item.severity == this.chosenSeverity.toUpperCase());
    }
  }


  filterByStatus() {
    if(this.chosenStatus == "undefined"){
      this.bugs = this.cachedBugs;
    }
    else{
      this.bugs = this.bugs.filter((item)=> item.status == this.chosenStatus.toUpperCase());
    }
  }
}
