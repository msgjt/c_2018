import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Permission} from "../types/permissions";
import {Observable} from "rxjs/internal/Observable";

@Injectable({
  providedIn: 'root'
})
export class PermissionService {
  baseURL = 'http://localhost:8080/jbugs/rest';
  tokenHeader = localStorage.getItem("userToken");
  constructor(private http: HttpClient) {

  }

  getAll(): Observable<any> {
    return this.http.get(this.baseURL + '/permissions',{
      headers:{'Authorization': this.tokenHeader}
    });
  }



}
