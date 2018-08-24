import {Component, OnInit} from '@angular/core';
import {User} from "../types/user";
import {Role} from "../types/roles";
import {UserService} from "../services/user.service";
import {RoleService} from "../services/role.service";
import {Permission} from "../types/permissions";

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  dropdownRoleList: Role[];
  selectedItems: Role [];
  dropdownSettings2 = {};
  showRoles: boolean;

  dropdownUserList: User[];
  selectedItem: User [];
  dropdownSettings1 = {};
  showUsers: boolean;
  showDetails: boolean;
  user: User;


  constructor(private userService: UserService, private rolesService: RoleService) {
    this.showRoles = false;
    this.showUsers = false;
    this.showDetails = false;

  }

  ngOnInit() {

    this.dropdownRoleList = [];
    this.dropdownUserList = [];
    this.selectedItems = [];
    this.selectedItem = [];

    this.rolesService.getRoles().subscribe((response: Role[]) => {
      response.forEach(value => this.dropdownRoleList.push(value))
    }, () => {
      console.log('errors')
    }, () => {
      this.showRoles = true;
    });

    this.userService.getUsers().subscribe((response: User[]) => {
      response.forEach(value => this.dropdownUserList.push(value))
    }, () => {
      console.log('errors')
    }, () => {
      this.showUsers = true;
    });


    this.dropdownSettings1 = {
      singleSelection: true,
      idField: 'id',
      textField: 'username',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      allowSearchFilter: true,
      maxHeight: 130,
    };

    this.dropdownSettings2 = {
      singleSelection: false,
      idField: 'id',
      textField: 'type',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      maxHeight: 130,
    };

  }

  clickUpdate() {

    console.log("aici1:");
    console.log(this.selectedItem[0]);

    this.userService.getUser(this.selectedItem[0].username).subscribe(user => {
      this.user = user;
      this.selectedItems=this.user.roles;
    }, (e) => {
      console.log('aparent am si o eroare');
    }, () => {
      this.showDetails = true;

    });


  }

  updateUser() {
    this.selectedItem[0]= this.user;
    this.userService.updateUser(this.selectedItem[0]);
  }

  setStatus(status: boolean) {
    this.user.isActive = status;
    console.log(this.user);
    console.log(status);
  }
}
