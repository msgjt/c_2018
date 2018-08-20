import { Component, OnInit } from '@angular/core';
import {Permission, PermissionService} from "../permission.service";

@Component({
  selector: 'app-permission',
  templateUrl: './permission.component.html',
  styleUrls: ['./permission.component.css']
})
export class PermissionComponent implements OnInit {


  constructor(private permissionService : PermissionService) {
    this.permissionService.getAll();
  }

  ngOnInit() {

  }

}
