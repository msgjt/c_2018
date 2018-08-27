import {Component, OnInit} from '@angular/core';
import {User} from "../types/user";
import {Role} from "../types/roles";
import {UserService} from "../services/user.service";
import {RoleService} from "../services/role.service";
import {Permission} from "../types/permissions";
import {PermissionService} from "../services/permission.service";

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


  showState: boolean;
  dropdownStatesList: string[] ;
  selectedState: string;
  dropdownSettings3 = {};

  checkSelect: boolean;
  permission: Permission[];

  constructor(private userService: UserService, private rolesService: RoleService) {
    this.showRoles = false;
    this.showUsers = false;
    this.showDetails = false;
    this.showState = false;
    this.checkSelect = false;


  }

  ngOnInit() {

    this.dropdownRoleList = [];
    this.dropdownUserList = [];
    this.selectedItems = [];
    this.selectedItem = [];
    this.permission = [];


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

    this.dropdownSettings3 = {
      singleSelection: true,
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
      this.selectedItems = this.user.roles;


    }, (e) => {
      console.log('aparent am si o eroare');
    }, () => {
      this.showDetails = true;
      this.showState = true;
      console.log(this.user.isActive);
      this.dropdownStatesList = this.user.isActive? ['DEACTIVATE']: ['ACTIVATE'];
    });
  }


  updateUser() {
    if (this.verifySelectMenu()) {
      this.selectedItem[0] = this.user;
      this.user.roles=this.selectedItems;


      this.user.roles.map(val=>val.permissions=this.permission);

      console.log(this.selectedItem[0]);
      this.userService.updateUser(this.selectedItem[0]);
    }
    else {
      this.checkSelect = true;
    }

  }

  verifySelectMenu(): boolean {
    return this.selectedItems.length > 0;
  }

  changeStatus(status: string) {
    console.log(status);

    if (status == "ACTIVATE") {
      this.user.isActive = true;
      console.log(this.user.isActive);
    }
    else {
      this.user.isActive = false;
      console.log(this.user.isActive);
    }
  }
}
