import {Component, OnInit} from '@angular/core';
import {RoleService} from "../services/role.service";
import {PermissionService} from "../services/permission.service";
import {Role} from "../types/roles";
import {Permission} from "../types/permissions";


@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {
  roles: Role[] = [];
  permissions: Permission[] = [];
  selectedItems: Permission[][] = [];
  dropdownSettings = {};
  roleComplete: boolean;
  permissionComplete: boolean;
  checkSelect: boolean[];

  constructor(private roleService: RoleService, private permissionService: PermissionService) {
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

  updatePermissions(role: Role) {
    if (this.verifySelectMenu(role)) {
      role.permissions = this.selectedItems[role.id];
      this.roleService.updateRole(role).subscribe((response: Role) => {
          this.checkSelect[response.id] = false;
        },
        () => {
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
        console.log('eroare getRoles')
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
        this.roles.forEach((value) => {
        });
        this.permissionComplete = true;
      });
  }

  verifySelectMenu(role: Role): boolean {
    return this.selectedItems[role.id].length > 0;
  }

}
