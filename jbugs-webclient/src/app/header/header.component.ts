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
  public toggle: boolean;

  constructor(private loginService: LoginService, private  translate: TranslateService, private filterService: FilterService) {
    this.toggle = false;
    this.translate.setDefaultLang('en');
  }

  ngOnInit() {

  }

  public isUserLoggedIn(): boolean {
    return localStorage.getItem("currentUser") != null;
  }


  public useLanguage(language: string) {
    this.translate.use(language);
  }

  public logout():void {
    this.loginService.logout();
  }

  public isPermissionManagement(): boolean {
    return this.filterService.isPermissionManagement();
  }

  public isUserManagement(): boolean {
    return this.filterService.isUserManagement()
  }

  public isBugManagement(): boolean {
    return this.filterService.isBugManagement();
  }

  public isBugClose(): boolean {
    return this.filterService.isBugClose();
  }

  public isBugExportPdf(): boolean {
    return this.filterService.isBugExportPdf();
  }

  public isAdressedUser(): boolean {
    return this.filterService.isAdressedUser();
  }

  public isReportManagement(): boolean {
    return this.filterService.isReportManagement();
  }

  public toggleSidebar():void {
    this.toggle = !this.toggle;
  }
}
