import {Component, OnInit} from '@angular/core';
import {RoleService} from "../services/role.service";
import {PermissionService} from "../services/permission.service";
import {Role} from "../types/roles";
import {Permission} from "../types/permissions";
import {AlertService} from "../services/alert.service";
import {HttpErrorResponse} from "@angular/common/http";


@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {
  public roles: Role[] = [];
  public permissions: Permission[] = [];
  public selectedItems: Permission[][] = [];
  public dropdownSettings = {};
  public roleComplete: boolean;
  public permissionComplete: boolean;
  public checkSelect: boolean[];

  constructor(private roleService: RoleService, private permissionService: PermissionService, private alertService:AlertService) {
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'type',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      maxHeight: 200
    };
    this.roleComplete = false;
    this.permissionComplete = false;
    this.checkSelect = [];


  }

  public updatePermissions(role: Role):void {
    if (this.verifySelectMenu(role)) {
      role.permissions = this.selectedItems[role.id];
      this.roleService.updateRole(role).subscribe((response: Role) => {
          this.checkSelect[response.id] = false;
          this.success("alerts.SUCCES-UPDATE");
        },
        (error:HttpErrorResponse) => {
          this.error("alerts."+error.error.toString());
          console.log('eroare update role')
        },
        () => {
          this.roleComplete = true;
        });
    }
    else {
      this.checkSelect[role.id] = true;
    }
  }


  ngOnInit() {

    this.roleService.getRoles().subscribe((response: Role[]) => {
        response.forEach((value) => {
          this.roles.push(value);
          this.selectedItems[value.id] = value.permissions;
          this.checkSelect[value.id] = false;
        })

      },
      () => {
        this.error("alerts.ERROR-SERVER");
      },
      () => {
        this.roleComplete = true;
      });
    this.permissionService.getAll().subscribe((response: Permission[]) => {
        response.forEach((value) => {
          this.permissions.push(value);
        });
      },
      () => {
        console.log('eroare dropdown ')
      },
      () => {
        this.permissionComplete = true;
      });
  }

  public verifySelectMenu(role: Role): boolean {
    return this.selectedItems[role.id].length > 0;
  }

  public success(message: string):void {
    this.alertService.success(message);
  }

  public error(message: string):void {
    this.alertService.error(message);
  }
}
