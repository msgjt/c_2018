import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

export interface User {
  username: string;
  password: string;
}

export interface Token {
  key: string;
}

export const TOKENKEY = 'userToken';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  baseURL = 'http://localhost:8080/jbugs/rest';

  constructor(private http: HttpClient) {
  }

  userAuthentication(userName, password): Observable<string> {
    var userModel = JSON.stringify({userName: userName, password: password});
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<any>(this.baseURL + '/authenticate', userModel, {headers: reqHeader});
  }

  isLoggedIn() {
    let username = localStorage.getItem(TOKENKEY);
    return username ? true : false;
  }

  login(token: string) {
    localStorage.setItem(TOKENKEY, token);
  }

  logout() {
    localStorage.removeItem(TOKENKEY);
  };
}
