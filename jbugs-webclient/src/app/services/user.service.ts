import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {User} from "../types/user";
import {Observable} from "rxjs/internal/Observable";
import {AlertService} from "./alert.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseURL = 'http://localhost:8080/jbugs/rest';
  tokenHeader:string = localStorage.getItem("userToken");
  constructor(private http: HttpClient,private alertService:AlertService) {
  }

  getAllUsers(): User[] {
    let users: User[] = [];
    this.http.get(this.baseURL + '/users',{
      headers:{'Authorization':this.tokenHeader}
    }).subscribe((response: User[]) => {
      response.forEach((value) => {
        users.push(value);
      })
    },(error:HttpErrorResponse)=>{
      this.alertService.error("alerts.ERROR-SERVER");
    });
    return users;
  }

  getUsers(): Observable<any> {
    return this.http.get(this.baseURL + '/users',{
      headers:{'Authorization':this.tokenHeader}
    });
  }

  getUser(username: String): Observable<User> {
    return this.http.get<User>(this.baseURL + '/users/' + username,{
      headers:{'Authorization':this.tokenHeader}
    });


  }

  addUser(user: User):Observable<User> {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json','Authorization': this.tokenHeader});
    var userModel = JSON.stringify(user);
    return this.http.post<any>(this.baseURL + '/users/add', userModel, {
      headers: reqHeader
    });
  }

  updateUser(user: User):Observable<User> {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json','Authorization': this.tokenHeader});
    var userModelUpdate = JSON.stringify(user);
    return this.http.put<any>(this.baseURL + '/users', userModelUpdate, {
      headers: reqHeader
    });
  }



}
