import {Component, OnInit} from '@angular/core';
import {LoginService, User} from "../services/login.service";
import {FilterService} from "../services/filter.service";
import {Router} from "@angular/router";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {UserService} from "../services/user.service";
import {Role} from "../types/roles";
import {Permission} from "../types/permissions";

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


  constructor(private loginService: LoginService, private filterService: FilterService, private router: Router, private http: HttpClient, private userService: UserService) {
    this.userModel = {
      username: '',
      password: ''
    };
    this.loggedIn = this.loginService.isLoggedIn();
  }

  /**
   * This method is use for submit login form
   */
  onSubmit() {
    this.http.post(this.baseURL + '/captcha', this.recaptchaResponse).subscribe((response) => {
      console.log(response);
      if (response['success'] == true) {
        console.log('Form was submitted with the following data:' +
          JSON.stringify(this.userModel));
      }
      this.loginService.userAuthentication(this.userModel.username, this.userModel.password).subscribe((response) => {
          if (response) {
            this.login(response, this.userModel.username);
            this.router.navigate(["home"]);
          } else {
            this.wrongCredentials = true;
            this.loggedIn = false;
          }
        },
        (err: HttpErrorResponse) => {
          console.log(err);
          this.wrongCredentials = true;
          this.loggedIn = false;
        });

    });
  }

  /**
   * This method set the loggedIn flag for the navigation bar
   * @param token represents Authetification token return from the server
   */
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
}
