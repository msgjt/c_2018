import {Component, OnInit} from '@angular/core';
import {LoginService, LSKEY, TOKENKEY, User} from '../login.service';
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

  submitForm() {
    console.log('Form was submitted with the following data:' +
      JSON.stringify(this.userModel));
    this.loginService.validateUserCredentials(this.userModel.username,
      this.userModel.password).subscribe((response) => {
      console.log('credentials are valid is : ' + response);
      if (response) {
        this.loggedIn = true;
        this.wrongCredentials = false;
        this.login(response.token);

      } else {
        this.wrongCredentials = true;
        this.loggedIn = false;
      }
    });
  }

  OnSubmit(){
    this.loginService.userAuthentication(this.userModel.username,this.userModel.password).subscribe((data : any)=>{
        localStorage.setItem('userToken',data.access_token);
      },
      (err : HttpErrorResponse)=>{
        this.wrongCredentials = true;
      });
  }

  login(token: string) {
    this.loginService.login(token, this.userModel);
    this.loggedIn = true;
    this.filterService.setLoggedIn(true);
  }

  logout() {
    localStorage.removeItem('userToken');
    this.router.navigate(['/login']);
  }

  ngOnInit() {
  }

}
