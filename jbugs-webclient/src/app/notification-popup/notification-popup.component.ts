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
  notificationList: Notification[] = [];
    /*[
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    },
    {
      type:'test',
      message: 'asdfdkhasdfhkgsadfkuffyuadsfkyug',
      date: new Date()
    }
  ];
*/
  constructor(private notificationService: NotificationService, public notificationData: NotificationDataService) {
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
    let username = localStorage.getItem('currentUser');
    this.notificationData.notifications = Array.from(this.notificationService.getAllNotifications(username));
  }

  loseFocus(){
    console.log(this.toggle)
    this.toggle = false;
  }
}
