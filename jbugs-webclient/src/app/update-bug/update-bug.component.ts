import { Component, OnInit } from '@angular/core';
import {Bug} from "../types/bugs";
import {BugService} from "../services/bug.service";

@Component({
  selector: 'app-update-bug',
  templateUrl: './update-bug.component.html',
  styleUrls: ['./update-bug.component.css']
})
export class UpdateBugComponent implements OnInit {

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
