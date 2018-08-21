import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Permission} from "../types/permissions";

@Injectable({
  providedIn: 'root'
})
export class PermissionService {
  permissions: Permission[] = [];
  baseURL = 'http://localhost:8080/jbugs/rest';

  constructor(private http: HttpClient) {
  }

  getAll(): Permission[] {
    this.permissions = [];
    this.http.get(this.baseURL + '/permissions',).subscribe((response: Permission[]) => {
      response.forEach((value) => {
        this.permissions.push(value);
      })
    });
    return this.permissions;
  }

  findByType(type: string): Permission {
      var permission:Permission;
      this.permissions.forEach((value) =>{
        if (value.type.trim() == type.trim()) {
          permission = value;
        }
      });
    return permission;
  }
}
