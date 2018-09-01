import {Injectable} from "@angular/core";
import {Notification} from "../types/notification";
import {Subject} from "rxjs/internal/Subject";
import {BehaviorSubject} from "rxjs/internal/BehaviorSubject";
import {Observable} from "rxjs/internal/Observable";

@Injectable()
export class NotificationDataService {
  private notificationsSubject: BehaviorSubject<Notification[]>;
  private dataStore: {
    notifications: Notification[];
}
  notifications$: Observable<any>;

  constructor(){
    this.dataStore = {notifications: []};
    this.notificationsSubject = <BehaviorSubject<Notification[]>>new BehaviorSubject([]);
    this.notifications$ = this.notificationsSubject.asObservable();
  }

  updateNotifications(newNotifications: Notification[]){
    this.dataStore.notifications = newNotifications;
    this.notificationsSubject.next(Object.assign({}, this.dataStore).notifications);
  }
}
