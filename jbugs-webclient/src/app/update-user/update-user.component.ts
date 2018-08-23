import { Component, OnInit } from '@angular/core';
import {User} from "../types/user";
import {Role} from "../types/roles";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  dropdownList: User[];
  selectedItem: User;
  roles: Role [];


  constructor(private userService: UserService) {

  }

  ngOnInit() {
      this.dropdownList=[];

  }

  updateUser(){
    
  }

}
