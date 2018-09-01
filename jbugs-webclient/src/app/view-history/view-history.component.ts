import {Component, OnInit} from '@angular/core';
import {BugService} from "../services/bug.service";
import {HistoryClass} from "../types/history";
import {HttpErrorResponse} from "@angular/common/http";
import {AlertService} from "../services/alert.service";

@Component({
  selector: 'app-view-history',
  templateUrl: './view-history.component.html',
  styleUrls: ['./view-history.component.css']
})
export class ViewHistoryComponent implements OnInit {

  histories: History[] = [];

  constructor(private bugService: BugService, private alertService: AlertService) {

  }

  ngOnInit() {
    this.bugService.getHistory().subscribe((response: History[]) => {
        response.forEach((value) => {
          this.histories.push(value);
        })
      }, (error: HttpErrorResponse) => {
        this.error("alerts.ERROR-SERVER");
      }
    )
  }

  public error(message: string): void {
    this.alertService.error(message);
  }

}
