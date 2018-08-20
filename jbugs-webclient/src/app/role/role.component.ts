import { Component, OnInit } from '@angular/core';
import {Role, RoleService} from "../services/role.service";

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {

  roles: Role[]= [];

  constructor(private roleService: RoleService) {
    this.roles = [];
  }

  ngOnInit() {
    this.roles = this.roleService.getAllRoles();
  }



}
