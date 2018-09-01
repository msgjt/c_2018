import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Notification} from "../types/notification";
import {NotificationService} from "../services/notification.service";
import {NotificationDataService} from "../services/notification-data.service";
import {Subscription} from "rxjs/internal/Subscription";

@Component({
  selector: 'app-notification',
  templateUrl: './notification-popup.component.html',
  styleUrls: ['./notification-popup.component.css']
})
export class NotificationPopupComponent implements OnInit{
  @Input() toggle: boolean;

  private subscription: Subscription;
  notifications: Notification[] = [];
  private first: boolean = false;

  constructor(public notificationData: NotificationDataService, private notificationService: NotificationService) {
    this.toggle = false;
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
    this.subscription = this.notificationData.notifications$
      .subscribe((state) => {
        this.notifications = state;
        if(this.first === false){
          this.first = true;
          this.updateNotifications();
        }
        }
      )
  }

  loseFocus(){
    console.log(this.toggle)
    this.toggle = false;
  }

  updateNotifications(){
    if(localStorage.getItem('currentUser') != null){
      let username = localStorage.getItem('currentUser');
      let newNotifications = [];
      this.notificationService.getAllNotifications(username).subscribe((response: Notification[]) =>{
        response.forEach((value => {
          newNotifications.push(value);
        }))
      });
      this.notificationData.updateNotifications(newNotifications);
    }

    setTimeout(()=>this.updateNotifications(),4000);
  }
}
