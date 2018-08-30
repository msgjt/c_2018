import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Notification} from "../types/notification";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  baseURL = 'http://localhost:8080/jbugs/rest/notification/';
  tokenHeader:string = localStorage.getItem("userToken");
  constructor(private http:HttpClient){}

  getAllNotifications(username: string):Notification[]{
    let notifications = [];
    this.http.get(this.baseURL + username, {
      headers:{'Authorization':this.tokenHeader}
    }).subscribe((response: Notification[]) =>{
      response.forEach((value => {
        notifications.push(value);
      }))
    });
    return notifications;
  }
}
