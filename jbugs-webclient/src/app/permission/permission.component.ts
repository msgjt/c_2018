import { Component, OnInit } from '@angular/core';
import {Permission, PermissionService} from "../services/permission.service";

@Component({
  selector: 'app-permission',
  templateUrl: './permission.component.html',
  styleUrls: ['./permission.component.css']
})
export class PermissionComponent implements OnInit {

  permissions: Permission[];

  constructor(private permissionService : PermissionService) {

  }

  ngOnInit() {
    this.permissions = this.permissionService.getAll();
  }

  OnSubmit(){

  }

}
