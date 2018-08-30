import { Component, OnInit } from '@angular/core';
import {BugService} from "../services/bug.service";
import {HistoryClass} from "../types/history";

@Component({
  selector: 'app-view-history',
  templateUrl: './view-history.component.html',
  styleUrls: ['./view-history.component.css']
})
export class ViewHistoryComponent implements OnInit {

  histories: History[]=[];
  constructor(private bugService:BugService) {

  }

  ngOnInit() {
    this.bugService.getHistory().subscribe((response:History[]) =>{
      response.forEach((value)=>{
        this.histories.push(value);
      })
    })
  }

}
