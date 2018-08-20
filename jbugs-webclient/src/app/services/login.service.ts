import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

export interface User {
  username: string;
  password: string;
}

export const LSKEY = 'currentUser';
export const TOKENKEY = 'webtoken';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  baseURL = 'http://localhost:8080/jbugs/rest';

  constructor(private http: HttpClient) { }

  validateUserCredentials(username: string, password: string): Observable<any> {

    let body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);

    return this.http.post(this.baseURL + '/authorize',
      body.toString(),
      {
        headers: new HttpHeaders(
          {'Content-Type': 'application/x-www-form-urlencoded'}
        )
      });
  }

  userAuthentication(userName, password) {
    var data = "username=" + userName + "&password=" + password + "&grant_type=password";
    var reqHeader = new HttpHeaders({ 'Content-Type': 'application/x-www-urlencoded','No-Auth':'True' });
    return this.http.post(this.baseURL + '/token', data, { headers: reqHeader });
  }

  isLoggedIn() {
    let username = localStorage.getItem(LSKEY);
    return username ? true : false;
  }

  login(token: string, userModel: User) {
    localStorage.setItem(LSKEY, userModel.username);
    localStorage.setItem(TOKENKEY, token);
  }

  logout() {
    if (localStorage.getItem(LSKEY)) {
      localStorage.removeItem(LSKEY);
      localStorage.removeItem(TOKENKEY);
    }
  }
}
