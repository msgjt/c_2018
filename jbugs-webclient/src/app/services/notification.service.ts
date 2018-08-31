import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Notification} from "../types/notification";
import {Observable} from "rxjs/internal/Observable";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  baseURL = 'http://localhost:8080/jbugs/rest/notification/';
  tokenHeader:string = localStorage.getItem("userToken");
  constructor(private http:HttpClient){}

  getAllNotifications(username: string):Observable<any>{
    let notifications = [];
    return this.http.get(this.baseURL + username, {
      headers:{'Authorization':this.tokenHeader}
    });
    //return notifications;
  }
}
