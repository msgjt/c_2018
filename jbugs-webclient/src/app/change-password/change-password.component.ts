import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {User} from "../types/user";
import {AlertService} from "../services/alert.service";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  oldPassword: string;
  newPassword: string;
  newPasswordAgain: string;
  baseURL = 'http://localhost:8080/jbugs/rest';

  constructor(private http: HttpClient, private alertService: AlertService) { }

  ngOnInit() {
    this.oldPassword = '';
    this.newPassword = '';
    this.newPasswordAgain = '';
  }

  updatePassword(){
    if(!this.newPassword.match("[A-Z]+[a-z]{5,}[0-9]+")){
      this.alertService.error("alerts.PASSWORD-NOT-STRONG")
    } else {
      if (!(this.newPassword == this.newPasswordAgain)) {
        this.alertService.error('alerts.PASSWORD-CONFIRMATION-MISSMATCH');
      }
      else {

        let username = localStorage.getItem("currentUser");
        var userModel = JSON.stringify({
          username: username,
          oldPassword: this.oldPassword,
          newPassword: this.newPassword
        });
        var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
        this.http.post<any>(this.baseURL + '/authenticate/changePassword', userModel, {headers: reqHeader}).subscribe(() => {
          this.success("alerts.SUCCES-ADD");
        }, (error: HttpErrorResponse) => {
          this.error('alerts.' + error.error.toString());
        }, () => {
          localStorage.removeItem("userToken");
          localStorage.removeItem("permissions");
          localStorage.removeItem("currentUser");
          window.location.reload();
        });
      }
    }
  }

  success(message: string) {
    this.alertService.success(message);
  }

  error(message: string) {
    this.alertService.error(message);
  }

}
