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
  notificationList: Notification[] = [
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

  constructor(public notificationData: NotificationDataService) { }

  ngOnInit() {
  }

}
