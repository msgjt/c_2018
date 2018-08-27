import {Injectable} from '@angular/core';
import {Role} from "../types/roles";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../types/user";
import {Observable} from "rxjs/internal/Observable";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseURL = 'http://localhost:8080/jbugs/rest';

  constructor(private http: HttpClient) {
  }

  getAllUsers(): User[] {
    let users: User[] = [];
    this.http.get(this.baseURL + '/users').subscribe((response: User[]) => {
      response.forEach((value) => {
        users.push(value);
      })
    });
    return users;
  }

  getUsers(): Observable<any> {
    return this.http.get(this.baseURL + '/users');
  }

  getUser(username: String): Observable<User> {
    return this.http.get<User>(this.baseURL + '/users/' + username);


  }
  /**
   * Make a request to the server for addUser
   * @param user represents user from component
   * @return added user
   */
  addUser(user: User): User {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    var userModel = JSON.stringify(user);
    this.http.post<any>(this.baseURL + '/users/add', userModel, {
      headers: reqHeader
    }).subscribe();
    return user;
  }

  updateUser(user: User) {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    var userModelUpdate = JSON.stringify(user);
    this.http.put<any>(this.baseURL + '/users', userModelUpdate, {
      headers: reqHeader
    }).subscribe();
  }



}
