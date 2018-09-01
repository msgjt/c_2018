import {Injectable} from "@angular/core";
import {Notification} from "../types/notification";
import {Subject} from "rxjs/internal/Subject";
import {BehaviorSubject} from "rxjs/internal/BehaviorSubject";
import {Observable} from "rxjs/internal/Observable";

@Injectable()
export class NotificationDataService {
  private notificationsSubject: BehaviorSubject<Notification[]>;
  private changed: BehaviorSubject<boolean>;
  private dataStore: {
    notifications: Notification[];
}
  notifications$: Observable<any>;
  changed$: Observable<any>;

  constructor(){
    this.dataStore = {notifications: []};
    this.notificationsSubject = <BehaviorSubject<Notification[]>>new BehaviorSubject([]);
    this.notifications$ = this.notificationsSubject.asObservable();
    this.changed = new BehaviorSubject<boolean>(false);
    this.changed$ = this.changed.asObservable();
  }

  updateNotifications(newNotifications: Notification[]){
    console.log("in update notification");
    console.log(this.dataStore.notifications);
    console.log(newNotifications);
    this.changedContent(this.dataStore.notifications.length !== newNotifications.length);
    this.dataStore.notifications = newNotifications;
    this.notificationsSubject.next(Object.assign({}, this.dataStore).notifications);
  }

  private changedContent(gotNewNotification: boolean){
    console.log("in change content: "+ gotNewNotification);
    this.changed.next(gotNewNotification);
  }
}
