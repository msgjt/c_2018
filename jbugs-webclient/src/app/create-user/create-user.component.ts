import {Component, OnInit} from '@angular/core';
import {$} from "jQuery";
import {Role} from "../types/roles";
import {RoleService} from "../services/role.service";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  roles: Role[] = [];
  dropdownList = [];
  selectedItems = [];
  dropdownSettings = {};

  constructor(private roleService: RoleService) {
  }

  onItemSelect(item: any) {
    console.log(item);
  }

  onSelectAll(items: any) {
    console.log(items);
  }

  show() {
    console.log(this.roles);
  }

  ngOnInit() {
    this.roles = this.roleService.getAllRoles();
    console.log(this.roles);
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

}
