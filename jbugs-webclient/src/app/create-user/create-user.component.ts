import {Component, OnInit} from '@angular/core';
import {$} from "jQuery";
import {User} from "../types/user";
import {UserService} from "../services/user.service";
import {RoleService} from "../services/role.service";
import {Role} from "../types/roles";
import {Permission, PermissionRest} from "../types/permissions";
import {AlertService} from "../services/alert.service";
import {HttpErrorResponse} from "@angular/common/http";

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
  checkSelect: boolean;

  constructor(private userService: UserService, private rolesService: RoleService, private alertService: AlertService) {
    this.initUser();
    this.showRoles = false;
  }

  /**
   * Init dropdown list with existing roles and set dropdown options
   */
  ngOnInit() {
    this.permission = [];
    this.dropdownList = [];
    this.selectedItems = [];
    this.checkSelect = false;
    this.rolesService.getRoles().subscribe((response: Role[]) => {
      response.forEach(value => this.dropdownList.push(value))
    }, () => {
      this.error("alerts.ERROR-SERVER");
      console.log('errors')
    }, () => {
      this.showRoles = true;
    });

    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'type',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      maxHeight: 110,
    };
  }

  /**
   * Get value of fields completed by user and call addUser from userService
   */
  addUser() {
    if (this.verifySelectMenu()) {
      this.user.roles = this.selectedItems;
      this.user.roles.map(value => value.permissions = this.permission);
      this.userService.addUser(this.user).subscribe((response: User) => {
        location.reload();
        this.success("alerts.SUCCES-ADD");
      }, (error: HttpErrorResponse) => {
        this.error('alerts.' + error.error.toString());
      }, () => {
        this.showRoles = true;

      });
      this.selectedItems = [];
    }
    else {
      this.checkSelect = true;
    }
  }

  verifySelectMenu(): boolean {
    return this.selectedItems.length > 0;
  }

  success(message: string) {
    this.alertService.success(message);
  }

  error(message: string) {
    this.alertService.error(message);
  }

  initUser() {
    this.user = {
      firstName: '',
      lastName: '',
      username: '',
      email: '',
      password: '',
      phoneNumber: '',
      isActive: true,
      roles: []
    };

  }

}
