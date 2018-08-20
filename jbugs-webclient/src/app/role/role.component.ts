import {Component, OnInit} from '@angular/core';
import {Role, RoleService} from "../services/role.service";
import {Permission, PermissionService} from "../services/permission.service";
import {$} from "jQuery";


@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {
  showAllPermissions: boolean[] = [];
  roles: Role[] = [];
  permissions: Permission[] = [];
  permissionsToBeUpdated: Permission[] = [];
  noPermissionsForThisRole: Permission[];
  isRemovedButtonSelected: boolean = false;

  constructor(private roleService: RoleService, private permissionService: PermissionService) {
    this.roles = [];
  }


  checkDelete(checked, type: string): Permission[] {
      var p:Permission= this.permissionService.findByType(type.trim());
      if (checked) {
        this.permissionsToBeUpdated.push(p);
        console.log(p.id);
      } else {
        let startIndex = this.permissionsToBeUpdated.indexOf(p);
        this.permissionsToBeUpdated.splice(startIndex, startIndex + 1);
        console.log("unchecked");
      }
    return this.permissionsToBeUpdated;
  }

  check(checked, p: Permission): Permission[] {
    if (checked) {
      this.permissionsToBeUpdated.push(p);
      console.log(p.id);
    } else {
      let startIndex = this.permissionsToBeUpdated.indexOf(p);
      this.permissionsToBeUpdated.splice(startIndex, startIndex + 1);
      console.log("unchecked");
    }
    return this.permissionsToBeUpdated;
  }

  updatePermissions(r: Role) {
    this.permissionsToBeUpdated.forEach((value, index) => {
      console.log(this.permissionsToBeUpdated[index]);
      this.roleService.addPermissionForRole(r.id, value.id);
      console.log(value.type);
    });
    location.reload();
  }


  updateRemovedPermissions(r: Role) {
    this.permissionsToBeUpdated.forEach((value, index) => {
      this.roleService.removePermissionForRole(r.id, value.id);
    });
    location.reload();
  }


  addPermissionForRole(r: Role) {
    this.showAllPermissions.forEach((value, index) => {
      this.showAllPermissions[index] = false
    });
    this.showAllPermissions[r.id] = true;
    this.permissionsToBeUpdated = [];
    this.isRemovedButtonSelected = false;
    this.noPermissionsForThisRole = [];
    this.permissions.forEach((value) => {
      if (!r.permissionsList.includes(value.type.trim())) {
        this.noPermissionsForThisRole.push(value);
      }
    })


  }

  removePermissionForRole(r: Role) {
    this.showAllPermissions.forEach((value, index) => {
      this.showAllPermissions[index] = false
    });
    this.showAllPermissions[r.id] = true;
    this.permissionsToBeUpdated = [];
    this.isRemovedButtonSelected = true;
    console.log("Removed method submitted");
  }

  backButton(r: Role){
    this.showAllPermissions[r.id] = !this.showAllPermissions[r.id];
  }

  ngOnInit() {

    this.roles = this.roleService.getAllRoles();
    this.permissions = this.permissionService.getAll();

  }


}
