import { Component, OnInit } from '@angular/core';
import {PermissionService} from "../services/permission.service";
import {Permission} from "../types/permissions";

@Component({
  selector: 'app-permission',
  templateUrl: './permission.component.html',
  styleUrls: ['./permission.component.css']
})
export class PermissionComponent implements OnInit {

  permissions: Permission[]

  constructor(private permissionService : PermissionService) {
    this.permissions=[];
  }



  ngOnInit() {
    this.permissionService.getAll().subscribe((response: Permission[]) => {
      response.forEach((value) => {
        this.permissions.push(value);
      })
    });
  }

  OnSubmit(){

  }

}
