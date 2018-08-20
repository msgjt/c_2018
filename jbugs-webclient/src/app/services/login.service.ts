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

  /**
   * Make a request to the server for user authentification
   * @param userName Value of username from the client form
   * @param password Value of password from the client form
   * @return Observable which represent the response from the server
   */
  userAuthentication(userName: string, password: string): Observable<any> {
    var userModel = JSON.stringify({userName: userName, password: password});
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<any>(this.baseURL + '/authenticate', userModel, {headers: reqHeader});
  }

  /**
   * Verify if current user is logged in
   * @return true or false
   */
  isLoggedIn(): boolean {
    let username = localStorage.getItem(TOKENKEY);
    return username ? true : false;
  }

  /**
   * Set the authentification token returned from server to local storage
   * @param token returned from server
   */
  login(token: string) {
    localStorage.setItem(TOKENKEY, token);
  }

  /**
   * Remove the authentification token of current user from server
   */
  logout() {
    localStorage.removeItem(TOKENKEY);
  };
}
