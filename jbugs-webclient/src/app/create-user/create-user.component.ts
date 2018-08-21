import { Component, OnInit } from '@angular/core';
import {$} from "jQuery";
import {Role} from "../types/roles";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  roles: Role[] = [];
  selectedItems: Role[] = [];

  constructor() { }
  ngOnInit() {
  }

  onItemSelect(role: Role){
    this.selectedItems.push(role);
  }

}
