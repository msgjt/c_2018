import { Component, OnInit } from '@angular/core';
import {PermissionService} from "../services/permission.service";
import {Permission} from "../types/permissions";

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
