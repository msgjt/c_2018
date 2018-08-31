import {AfterViewInit, Component, OnInit} from '@angular/core';
import {NotificationDataService} from "./services/notification-data.service";
import {NotificationService} from "./services/notification.service";
import {Notification} from "./types/notification";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'JBuggerAngular';
  newNotifications: Notification[];

  constructor(private notificationData: NotificationDataService, private notificationService: NotificationService) {
    this.getAllNotifications();
  }

  ngOnInit(): void {
  }

  getAllNotifications() {
    let username='';
    if (localStorage.getItem("currentUser")!== null) {
      let username = localStorage.getItem("currentUser");
      this.notificationData.notifications = [];
      this.notificationService.getAllNotifications(username).subscribe((response: Notification[]) => {
        response.forEach((value => {
          this.notificationData.notifications.push(value);
        }))

      });
    }
    setTimeout(() => {
      this.getAllNotifications();
    }, 30000);
  }
}
