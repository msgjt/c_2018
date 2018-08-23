import { Component, OnInit } from '@angular/core';
import {Bug} from "../types/bugs";
import {BugService} from "../services/bug.service";
import {$} from "protractor";
import {e} from "@angular/core/src/render3";

@Component({
  selector: 'app-update-bug',
  templateUrl: './update-bug.component.html',
  styleUrls: ['./update-bug.component.css']
})
export class UpdateBugComponent implements OnInit {

  bugs: Bug[];
  isEditable: boolean[] = [];
  cachedBugs: Bug[];
  severities:string[] = ["critical", "high", "medium", "low"];
  chosenSeverity:string;
  statuses:string[] = ["fixed", "open", "in_progress", "rejected", "info_nedded", "closed"];
  chosenStatus:string;
  bug : Bug;

  constructor(private bugService: BugService){
  }

  ngOnInit(): void {
    this.bugs = this.bugService.getAll();
    this.cachedBugs = this.bugs;
    this.bugs.forEach((value)=>{
      this.isEditable[value.idBug] = false;
    })
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



  onSubmit(bug: Bug){
    if(this.isEditable[bug.idBug]){
      console.log('Bug updated');
      this.bugService.updateBug(bug);
    }
    else{
      console.log('Bug ready to be updated');
    }
    this.isEditable[bug.idBug] =!this.isEditable[bug.idBug];
    console.log(bug.title);
    this.bugs.forEach((value)=>{
      if(value.idBug!==bug.idBug)
        this.isEditable[value.idBug] = false;
    })


  }

  editableFunction(bug:Bug):boolean{
    return this.isEditable[bug.idBug];


  }


}
