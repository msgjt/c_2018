import {Component, OnInit} from '@angular/core';
import {LoginService} from '../services/login.service';
import {LoginComponent} from '../login/login.component';
import {FilterService} from '../services/filter.service';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  loggedIn = false;

  constructor(private loginService: LoginService, private filterService: FilterService, private  translate: TranslateService) {
    this.translate.setDefaultLang('en');
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

  useLanguage(language: string) {
    this.translate.use(language);
  }

}
