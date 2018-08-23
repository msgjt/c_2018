import {Component, OnInit} from '@angular/core';
import {$} from "jQuery";
import {User} from "../types/user";
import {UserService} from "../services/user.service";
import {RoleService} from "../services/role.service";
import {Role} from "../types/roles";
import {Permission, PermissionRest} from "../types/permissions";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  dropdownList: Role[];
  selectedItems: Role[];
  dropdownSettings = {};
  user: User;
  showRoles: boolean;
  permission: Permission[];

  constructor(private userService: UserService, private rolesService: RoleService) {
    this.user = {
      firstName: '',
      lastName: '',
      username: '',
      email: '',
      password: '',
      phoneNumber: '',
      roles: []
    };
    this.showRoles = false;
  }

  /**
   * Init dropdown list with existing roles and set dropdown options
   */
  ngOnInit() {
    this.permission = [];
    this.dropdownList = [];
    this.selectedItems = [];
    this.rolesService.getRoles().subscribe((response: Role[]) => {
      response.forEach(value => this.dropdownList.push(value))
    }, () => {
      console.log('errors')
    }, () => {
      this.showRoles=true;
    });

    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'type',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      maxHeight: 130
    };
  }

  /**
   * Get value of fields completed by user and call addUser from userService
   */
  addUser() {
    if(this.verifySelectMenu()) {
      this.user.roles = this.selectedItems;
      this.user.roles.map(value => value.permissions = this.permission);
      this.userService.addUser(this.user);
      this.selectedItems=[];
    }
  }

  verifySelectMenu():boolean{
    return this.selectedItems.length>0;
  }

}
