import {Component, OnInit} from '@angular/core';
import {User} from "../types/user";
import {Role} from "../types/roles";
import {UserService} from "../services/user.service";
import {RoleService} from "../services/role.service";
import {Permission} from "../types/permissions";
import {AlertService} from "../services/alert.service";
import {HttpErrorResponse} from "@angular/common/http";


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
  status: string;

  dropdownUserList: User[];
  selectedItem: User [];
  dropdownSettings1 = {};
  showUsers: boolean;
  showDetails: boolean;
  user: User;


  showState: boolean;
  dropdownSettings3 = {};

  checkSelect: boolean;
  permission: Permission[];

  constructor(private userService: UserService, private rolesService: RoleService, private alertService: AlertService) {
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
      this.error("alerts.ERROR-SERVER");
    }, () => {
      this.showRoles = true;
    });

    this.userService.getUsers().subscribe((response: User[]) => {
      response.forEach(value => { let user: User = value;
                                          user.fullname = value.username + ' : ' + value.firstName + ' ' + value.lastName;
                                          this.dropdownUserList.push(user);

                                          } )
    }, () => {
      this.error("alerts.ERROR-SERVER");
    }, () => {
      this.showUsers = true;
    });


    this.dropdownSettings1 = {
      singleSelection: true,
      idField: 'id',
      textField: 'fullname',
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

    this.userService.getUser(this.selectedItem[0].fullname.split(':')[0].trim()).subscribe(user => {
      this.user = user;
      if (this.user.isActive == true) {
        this.status = 'ACTIVE';
      } else {
        this.status = 'INACTIVE';
      }
      this.selectedItems = this.user.roles;


    }, (error: HttpErrorResponse) => {
      this.error("alerts." + error.error.toString());
    }, () => {
      this.showDetails = true;
      this.showState = true;

    });
  }


  updateUser() {
    if (this.verifySelectMenu()) {

      this.selectedItem[0] =this.user;
      this.user.roles=this.selectedItems;

      this.user.roles.map(val=>val.permissions=this.permission);

      var element = <HTMLInputElement> document.getElementById("changeStatus");
      var isChecked = element.checked;
      if(isChecked){
        this.selectedItem[0].isActive=!this.selectedItem[0].isActive;
      }
      this.userService.updateUser(this.selectedItem[0]).subscribe(user => {
        this.success("alerts.SUCCES-UPDATE");
        window.location.reload();
        this.selectedItems = [];
        this.selectedItem = [];
      }, (error: HttpErrorResponse) => {
        this.error("alerts." + error.error.toString());
      });

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

}
