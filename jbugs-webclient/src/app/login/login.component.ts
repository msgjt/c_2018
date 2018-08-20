import {Component, OnInit} from '@angular/core';
import {LoginService, LSKEY, Token, TOKENKEY, User} from '../login.service';
import {FilterService} from '../filter.service';
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userModel: User;
  wrongCredentials = false;
  loggedIn = false;


  constructor(private loginService: LoginService, private filterService: FilterService, private router: Router) {
    this.userModel = {
      username: '',
      password: ''
    };
    this.loggedIn = this.loginService.isLoggedIn();
  }

  OnSubmit() {
    this.loginService.userAuthentication(this.userModel.username, this.userModel.password).subscribe((response) => {
        localStorage.setItem('userToken', response.key);
        if (response) {
          this.login(response.key);
        } else {
          this.wrongCredentials = true;
          this.loggedIn = false;
        }
      },
      (err: HttpErrorResponse) => {
        console.log(err)
      });
  }

  login(token: string) {
    this.loginService.login(token);
    this.loggedIn = true;
    this.filterService.setLoggedIn(true);
  }

  ngOnInit() {
  }

}
