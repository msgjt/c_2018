import { Component, OnInit } from '@angular/core';
import {User} from "../types/user";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-add-bug-component',
  templateUrl: './add-bug-component.component.html',
  styleUrls: ['./add-bug-component.component.css']
})
export class AddBugComponentComponent implements OnInit {

  severity: string[] = ["CRITICAL","HIGH","MEDIUM","LOW"];
  chosenSeverity: string;
  chosenUsername: string;
  allUsers: User[] = [];

  constructor(private userService:UserService) {}

  changedSelected(chosenSeverity: string){
    console.log(chosenSeverity);
  }

  changedSelectedUsername(){
    console.log(this.chosenUsername);
}

  ngOnInit() {
    this.allUsers = this.userService.getAllUsers();
  }



}
