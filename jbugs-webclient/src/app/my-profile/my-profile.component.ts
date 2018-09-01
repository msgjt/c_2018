import {Component, OnInit} from '@angular/core';
import {User} from "../types/user";
import {UserService} from "../services/user.service";
import {AlertService} from "../services/alert.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {
  status: String;
  showUpdatePassword: boolean;
  user: User;
  gotRoles: boolean;
  okToShow: boolean;

  constructor(private userService: UserService, private alertService: AlertService) {
  }

  ngOnInit() {
    this.showUpdatePassword = false;
    this.userService.getUser(localStorage.getItem("currentUser")).subscribe(value => {
        this.user = value;
        this.status = this.user.isActive ? 'ACTIVE' : 'INACTIVE';
        this.gotRoles = true;
        this.okToShow = true;
      },
      (error: HttpErrorResponse) => {
        this.error("alerts.ERROR-SERVER");
      });

  }

  error(message: string) {
    this.alertService.error(message);
  }


}
