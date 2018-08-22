import {Component, OnInit} from '@angular/core';
import {$} from "jQuery";
import {User} from "../types/user";
import {UserService} from "../services/user.service";
import {RoleService} from "../services/role.service";
import {Role} from "../types/roles";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  dropdownList = [];
  selectedItems = [];
  roles = [];
  dropdownSettings = {};
  user: User;
  showRoles: boolean;

  constructor(private userService: UserService, private rolesService: RoleService) {
    this.user = {
      firstName: '',
      lastName: '',
      userName: '',
      email: '',
      password: '',
      phoneNumber: '',
      rolesList: []
    };
    this.showRoles = false;
  }

  /**
   * Init dropdown list with existing roles and set dropdown options
   */
  ngOnInit() {

    this.rolesService.getRoles().subscribe((response: Role[]) => {
      response.forEach(value => this.dropdownList.push({id: value.id, type: value.type}))
    }, () => {
      console.log('errors')
    }, () => {
      console.log(this.dropdownList.length)
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
    for (let role of this.selectedItems) {
      this.roles.push(role.type);
    }
    this.user.rolesList = this.roles;
    this.roles = [];
    this.userService.addUser(this.user);
  }

  show() {
    console.log(this.rolesService.getAllRoles());
    this.showRoles = true;

  }

}
