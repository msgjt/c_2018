import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Role} from "../types/roles";
import {Observable} from "rxjs/internal/Observable";

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  baseURL = 'http://localhost:8080/jbugs/rest';
  roles :Role[] = [];
  tokenHeader = localStorage.getItem("userToken");
  constructor(private http: HttpClient) {
  }

  getAllRoles(): Role[] {
    let roles :Role[] = [];
    this.http.get(this.baseURL + '/roles',{
      headers:{'Authorization': this.tokenHeader}
    }).subscribe((response: Role[]) => {
      response.forEach((value) => {
        roles.push(value);
      })
    });
    return roles;
  }

  getRoles(): Observable<any> {
    return this.http.get(this.baseURL + '/roles',{
      headers:{'Authorization': this.tokenHeader}});
  }

  addPermissionForRole(idRole: number, idPermission: number) {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json','Authorization':this.tokenHeader});
    var idPermissionModel = JSON.stringify({id: idPermission});
    this.http.post<any>(this.baseURL + '/roles/add/' + idRole,idPermissionModel , {
      headers: reqHeader
    }).subscribe();
  }

  removePermissionForRole(idRole: number, idPermission: number) {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json','Authorization':this.tokenHeader});
    var idPermissionModel = JSON.stringify({id: idPermission});
    this.http.post<any>(this.baseURL + '/roles/remove/' + idRole,idPermissionModel , {
      headers: reqHeader
    }).subscribe();
  }

}
