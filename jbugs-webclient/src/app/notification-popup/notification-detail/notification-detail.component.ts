import { Component, OnInit } from '@angular/core';
import {Notification} from "../../types/notification";
import {NotificationDataService} from "../../services/notification-data.service";

@Component({
  selector: 'app-notification-detail',
  templateUrl: './notification-detail.component.html',
  styleUrls: ['./notification-detail.component.css']
})
export class NotificationDetailComponent implements OnInit {
  page: number=1;


  constructor(public notificationData: NotificationDataService) { }

  ngOnInit() {
  }

}
