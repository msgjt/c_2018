import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Role} from "../types/roles";


@Injectable({
  providedIn: 'root'
})
export class RoleService {
  roles: Role[] = [];
  baseURL = 'http://localhost:8080/jbugs/rest';

  constructor(private http: HttpClient) {
  }

  getAllRoles(): Role[] {
    this.http.get(this.baseURL + '/roles',).subscribe((response: Role[]) => {
      response.forEach((value) => {
        this.roles.push(value);
      })
    });
    return this.roles;
  }

  addPermissionForRole(idRole: number, idPermission: number) {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    var idPermissionModel = JSON.stringify({id: idPermission});
    this.http.post<any>(this.baseURL + '/roles/add/' + idRole,idPermissionModel , {
      headers: reqHeader
    }).subscribe();
  }

  removePermissionForRole(idRole: number, idPermission: number) {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    var idPermissionModel = JSON.stringify({id: idPermission});
    this.http.post<any>(this.baseURL + '/roles/remove/' + idRole,idPermissionModel , {
      headers: reqHeader
    }).subscribe();
  }

}
