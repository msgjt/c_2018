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
  showAllPermissions: boolean[] = [];
  roles: Role[] = [];
  permissions: Permission[] = [];
  selectedItems: Permission[][]=[];
  permissionsToBeUpdated: Permission[] = [];
  noPermissionsForThisRole: Permission[];
  isRemovedButtonSelected: boolean = false;
  dropdownSettings={};

  constructor(private roleService: RoleService, private permissionService: PermissionService) {
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




  findByType(type: string): Permission {
    console.log(this.permissions.length);
    let permission: Permission;
    permission = this.permissions.filter(value => {
      return value.type.trim() === type.trim();

    })[0];
    return permission;
  }

  selectPermissions(role: Role) {
    return this.selectedItems[role.id] = role.permissions;
  }

  checkDelete(checked, type: string): Permission[] {
    var permission: Permission = this.findByType(type.trim());
    console.log(permission);
    if (checked) {
      this.permissionsToBeUpdated.push(permission);
    } else {
      let startIndex = this.permissionsToBeUpdated.indexOf(permission);
      this.permissionsToBeUpdated.splice(startIndex, startIndex + 1);
    }
    return this.permissionsToBeUpdated;
  }

  check(checked, permission: Permission): Permission[] {
    if (checked) {
      this.permissionsToBeUpdated.push(permission);
      console.log(permission.id);
    } else {
      let startIndex = this.permissionsToBeUpdated.indexOf(permission);
      this.permissionsToBeUpdated.splice(startIndex, startIndex + 1);
      console.log("unchecked");
    }
    return this.permissionsToBeUpdated;
  }

  updatePermissions(role: Role) {
    this.selectedItems[role.id].forEach((value, index) => {
      console.log(this.permissionsToBeUpdated[index]);
      this.roleService.addPermissionForRole(role.id, value.id);
      console.log(value.type);
    });
    location.reload();
  }


  updateRemovedPermissions(role: Role) {
    console.log(this.permissionsToBeUpdated.length);
    this.permissionsToBeUpdated.forEach((value) => {
      console.log(value);
      this.roleService.removePermissionForRole(role.id, value.id);
    });
    location.reload();
  }


  addPermissionForRole(role: Role) {
    this.showAllPermissions.forEach((value, index) => {
      this.showAllPermissions[index] = false
    });
    this.showAllPermissions[role.id] = true;
    this.permissionsToBeUpdated = [];
    this.isRemovedButtonSelected = false;
    this.noPermissionsForThisRole = [];
    this.permissions.forEach((value) => {
      if (!this.contains(role.permissions,value)) {
          this.noPermissionsForThisRole.push(value);
      }
    })
  }

  removePermissionForRole(role: Role) {
    this.permissionsToBeUpdated = [];
    this.showAllPermissions.forEach((value, index) => {
      this.showAllPermissions[index] = false
    });
    this.showAllPermissions[role.id] = true;

    this.isRemovedButtonSelected = true;
    console.log("Removed method submitted");
  }

  backButton(role: Role) {
    this.showAllPermissions[role.id] = !this.showAllPermissions[role.id];
  }

  ngOnInit() {

    this.roles = this.roleService.getAllRoles();
    this.permissionService.getAll().subscribe((response: Permission[]) => {
      response.forEach((value) => {
        this.permissions.push(value);
      })
    });
    this.roles.forEach((value) =>{
      this.selectPermissions(value);
    })
  };

  /**
   * Make list of string with permissions types
   @param permissions Permissions list
   */
  showPermission(permissions: Permission[]): string[] {
    let permission = [];
    for (let p of permissions) {
      permission.push(p.type);
    }
    return permission;
  }

  /**
   * Verify if permissions list contains a permission
   @param permissions Permissions list
   @param permission  Permission for looking
   */
  contains(permissions:Permission[],permission:Permission):boolean{
    for(let p of permissions){
      if(p.type===permission.type){
        return true;
      }
    }
    return false;
  }


}
