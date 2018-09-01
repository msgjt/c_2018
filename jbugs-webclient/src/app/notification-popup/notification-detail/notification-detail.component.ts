import { Component, OnInit } from '@angular/core';
import {Notification} from "../../types/notification";
import {NotificationDataService} from "../../services/notification-data.service";
import {Subscription} from "rxjs/internal/Subscription";

@Component({
  selector: 'app-notification-detail',
  templateUrl: './notification-detail.component.html',
  styleUrls: ['./notification-detail.component.css']
})
export class NotificationDetailComponent implements OnInit {
  page: number=1;
  notifications: Notification[] = [];
  private subscription: Subscription;


  constructor(public notificationData: NotificationDataService) { }

  ngOnInit() {
    this.subscription = this.notificationData.notifications$
      .subscribe((state) => {
          this.notifications = state;
        }
      )
  }

}
