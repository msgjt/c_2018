import {Component, Input, OnInit} from '@angular/core';
import {Notification} from "../types/notification";
import {NotificationService} from "../services/notification.service";
import {NotificationDataService} from "../services/notification-data.service";

@Component({
  selector: 'app-notification',
  templateUrl: './notification-popup.component.html',
  styleUrls: ['./notification-popup.component.css']
})
export class NotificationPopupComponent implements OnInit {
  @Input() toggle: boolean;

  constructor(public notificationData: NotificationDataService,private notificationService: NotificationService) {
    this.toggle = false;
    this.getAllNotifications();
   // this.initClickEvent();
  }
/*

  initClickEvent() {
    //js
    window.document.addEventListener('click', function(target){
      if(!document.getElementById("wrapper").contains(target.target) && document.getElementById("sidebar-toggle-button")!== target.target) {
        this.toggle = false;
      }
    }.bind(this));
  }
*/

  ngOnInit() {

  }

  loseFocus(){
    console.log(this.toggle)
    this.toggle = false;
  }

  getAllNotifications() {
    let username = localStorage.getItem("currentUser");
    if (localStorage.getItem("currentUser")!== null) {
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
