import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";


export interface Permission {

  type:string;
  description :string;

}

@Injectable({
  providedIn: 'root'
})
export class PermissionService {
  baseURL = 'http://localhost:8080/jbugs/rest';
  constructor(private http: HttpClient) { }

  getAll() {
    
    return this.http.get(this.baseURL + '/permissions',).subscribe();
  }

}
