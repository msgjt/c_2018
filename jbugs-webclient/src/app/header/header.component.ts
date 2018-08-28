import {Component, OnInit} from '@angular/core';
import {LoginService} from '../services/login.service';
import {FilterService} from '../services/filter.service';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  toggle: boolean;

  constructor(private loginService: LoginService, private  translate: TranslateService, private filterService: FilterService) {
    this.toggle = false;
    this.translate.setDefaultLang('en');
  }

  ngOnInit() {

  }

  isUserLoggedIn(): boolean {
    return localStorage.getItem("currentUser") != null;
  }


  useLanguage(language: string) {
    this.translate.use(language);
  }

  logout() {
    this.loginService.logout();
  }

  isPermissionManagement(): boolean {
    return this.filterService.isPermissionManagement();
  }

  isUserManagement(): boolean {
    return this.filterService.isUserManagement()
  }

  isBugManagement(): boolean {
    return this.filterService.isBugManagement();
  }

  isBugClose(): boolean {
    return this.filterService.isBugClose();
  }

  isBugExportPdf(): boolean {
    return this.filterService.isBugExportPdf();
  }

  isAdressedUser(): boolean {
    return this.filterService.isAdressedUser();
  }

  isReportManagement(): boolean {
    return this.filterService.isReportManagement();
  }

  toggleSidebar() {
    this.toggle = !this.toggle;
  }
}
