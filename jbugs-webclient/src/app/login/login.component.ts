import {Component, OnInit} from '@angular/core';
import {LoginService, User} from "../services/login.service";
import {FilterService} from "../services/filter.service";
import {Router} from "@angular/router";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {UserService} from "../services/user.service";
import {Role} from "../types/roles";
import {Permission} from "../types/permissions";
import {AlertService} from "../services/alert.service";
import {NotificationService} from "../services/notification.service";
import {NotificationDataService} from "../services/notification-data.service";
import {Notification} from "../types/notification";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userModel: User;
  wrongCredentials = false;
  loggedIn = false;
  recaptchaResponse: any;
  baseURL = 'http://localhost:8080/jbugs/rest';


  constructor(private notificationService: NotificationService, private notificationData: NotificationDataService, private loginService: LoginService, private filterService: FilterService, private router: Router, private http: HttpClient, private userService: UserService,private alertService: AlertService) {
    this.userModel = {
      username: '',
      password: ''
    };
    this.loggedIn = this.loginService.isLoggedIn();
  }


  onSubmit() {
    this.http.post( this.baseURL + '/captcha', this.recaptchaResponse).subscribe((response) => {
      console.log(response);
      if (response['success'] == true) {
        console.log('Form was submitted with the following data:' +
          JSON.stringify(this.userModel));
      }
      this.loginService.userAuthentication(this.userModel.username, this.userModel.password).subscribe((response) => {
          if (response) {
            this.login(response, this.userModel.username);
            this.router.navigate(["home"]);
            this.succes("alerts.SUCCES-LOGIN");
            this.getAllNotifications(localStorage.getItem("currentUser"));
          } else {
            this.wrongCredentials = true;
            this.loggedIn = false;

          }
        },
        (err: HttpErrorResponse) => {
          console.log(err);
          this.wrongCredentials = true;
          this.loggedIn = false;
          this.error("alerts.FAILED-LOGIN")
        });

    });
  }


  login(token: string, username: string) {
    this.userService.tokenHeader=token;
    this.loginService.login(token, username);
    this.loggedIn = true;
    this.filterService.setLoggedIn(true);
    this.userService.getUser(this.userModel.username).subscribe((response) => {
      if (response) {
        this.loginService.setPermissions(this.getPermissionsForUser(response.roles));
      }
      else
        console.log("getUser");
    });
  }

  ngOnInit() {
  }

  resolved(captchaResponse: string) {
    this.recaptchaResponse = captchaResponse;
  }

  getPermissionsForUser(roles: Role[]): Permission[] {
    let permissionList: Permission[] = [];
    for (let role of roles) {
      for (let permission of role.permissions) {
        if (!permissionList.includes(permission)) {
          permissionList.push(permission);
        }
      }
    }
    return permissionList;
  }

  getAllNotifications(username:string){
    this.notificationData.notifications=[];
    this.notificationService.getAllNotifications(username).subscribe((response: Notification[]) =>{
      response.forEach((value => {
        this.notificationData.notifications.push(value);
      }))
    });
    setTimeout(()=>{
      this.getAllNotifications(username);
    }, 30000);
  }

  error(message: string) {
    this.alertService.error(message);
  }
  succes(message: string){
    this.alertService.success(message);
  }
}
