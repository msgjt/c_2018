import {AfterViewInit, Component, OnInit} from '@angular/core';
import {NotificationDataService} from "./services/notification-data.service";
import {NotificationService} from "./services/notification.service";
import {Notification} from "./types/notification";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit{
  title = 'JBuggerAngular';
  newNotifications: Notification[];
  constructor(private notificationData: NotificationDataService, private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    //setTimeout(this.updateNotifications(),6000);
  }


  updateNotifications(){
    let username = localStorage.getItem('currentUser');
    if(username != null){
      //console.log(username);
      this.newNotifications = [];
      this.notificationService.getAllNotifications(username).subscribe((response: Notification[]) =>{
        response.forEach((value => {
          this.newNotifications.push(value);
          console.log(response)
        }))
      });
      //console.log('notifications: ' + this.newNotifications.toString());
      this.notificationData.updateNotifications(this.newNotifications);
    }

    //setTimeout(this.updateNotifications(),600000);
  }

  ngAfterViewInit(): void {
    //setTimeout(this.updateNotifications(),50000);
  }
}
