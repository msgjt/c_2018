import {AfterViewInit, Component, OnInit} from '@angular/core';
import {NotificationDataService} from "./services/notification-data.service";
import {NotificationService} from "./services/notification.service";
import {Notification} from "./types/notification";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'JBuggerAngular';

  constructor() {
  }

  ngOnInit(): void {
  }

}
