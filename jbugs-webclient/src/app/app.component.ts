import {Component, OnInit} from '@angular/core';
import {NotificationDataService} from "./services/notification-data.service";
import {NotificationService} from "./services/notification.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'JBuggerAngular';

  constructor(private notificationData: NotificationDataService, private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    setTimeout(this.updateNotifications(),10000);
  }

  updateNotifications(){
    let username = localStorage.getItem('currentUser');
    this.notificationService.getAllNotifications(username);
    //setTimeout(this.updateNotifications(),10000);
  }
}
