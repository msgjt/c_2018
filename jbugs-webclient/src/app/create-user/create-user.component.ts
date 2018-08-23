import {Component, OnInit} from '@angular/core';
import {User} from "../types/user";
import {UserService} from "../services/user.service";

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

  constructor(private userService: UserService) {
    this.user = {
      firstName: '',
      lastName: '',
      userName: '',
      email: '',
      password: '',
      phoneNumber: '',
      rolesList: []
    };
  }

  /**
   * Init dropdown list with existing roles and set dropdown options
   */
  ngOnInit() {
    this.dropdownList = [
      {idRole: 1, type: 'Administrator'},
      {idRole: 2, type: 'Project manager'},
      {idRole: 3, type: 'Test manager'},
      {idRole: 4, type: 'Developer'},
      {idRole: 5, type: 'Tester'}
    ];
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'idRole',
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

}
