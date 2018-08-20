import {Component, OnInit} from '@angular/core';
import {LoginService} from '../services/login.service';
import {LoginComponent} from '../login/login.component';
import {FilterService} from '../services/filter.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  loggedIn = false;

  constructor(private loginService: LoginService, private filterService: FilterService) {
  }

  ngOnInit() {
    this.loggedIn = this.filterService.isLoggedIn();
  }

  verify(): boolean {
    return this.filterService.isLoggedIn();
  }

  logout() {
    this.loginService.logout();
  }

}
