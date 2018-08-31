import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Role} from "../types/roles";
import {Observable} from "rxjs/internal/Observable";

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  baseURL = 'http://localhost:8080/jbugs/rest';
  roles: Role[] = [];

  constructor(private http: HttpClient) {
  }

  getRoles(): Observable<any> {
    return this.http.get(this.baseURL + '/roles');
  }

  updateRole(role:Role): Observable<any> {
    var roleModel = JSON.stringify(role);
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<any>(this.baseURL + '/roles', roleModel, {headers: reqHeader});
  }

}
