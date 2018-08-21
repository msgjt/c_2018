import { Injectable } from '@angular/core';
import {Role} from "../types/roles";
import {HttpClient} from "@angular/common/http";
import {User} from "../types/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseURL = 'http://localhost:8080/jbugs/rest';
  constructor(private http: HttpClient) { }


  getAllUsers(): User[] {
    let users :User[] = [];
    this.http.get(this.baseURL + '/users').subscribe((response: User[]) => {
      response.forEach((value) => {
        users.push(value);
      })
    });
    return users;
  }

}
