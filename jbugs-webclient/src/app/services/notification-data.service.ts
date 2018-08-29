import {Injectable} from "@angular/core";
import {Notification} from "../types/notification";

@Injectable()
export class NotificationDataService {
  private _notifications: Notification[];


  get notifications(): Notification[] {
    return this._notifications;
  }

  set notifications(value: Notification[]) {
    this._notifications = value;
  }
}
