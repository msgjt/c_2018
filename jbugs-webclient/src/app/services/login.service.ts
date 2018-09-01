import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Permission} from "../types/permissions";

export interface User {
  username: string;
  password: string;
}

export interface Token {
  key: string;
}

export const TOKENKEY = 'userToken';
export const CURRENTUSER = 'currentUser';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  baseURL = 'http://localhost:8080/jbugs/rest';

  constructor(private http: HttpClient) {
  }


  userAuthentication(userName: string, password: string): Observable<any> {
    var userModel = JSON.stringify({username: userName, password: password});
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<any>(this.baseURL + '/authenticate', userModel, {headers: reqHeader});
  }


  isLoggedIn(): boolean {
    let username = localStorage.getItem(TOKENKEY);
    return username ? true : false;
  }


  login(token: string, username: string ) {
    localStorage.setItem(TOKENKEY, token);
    localStorage.setItem(CURRENTUSER,username);

  }


  logout() {
    localStorage.removeItem(TOKENKEY);
    localStorage.removeItem(CURRENTUSER);
    localStorage.setItem("permissions",'');
  };

  setPermissions(permissions:string[]){
    let permissionString:string='';
    permissions.map(value => permissionString=permissionString+' '+value);
    localStorage.setItem('permissions',permissionString);
    console.log(permissionString);
  }

}
