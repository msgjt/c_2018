import {Injectable} from '@angular/core';
import {TOKENKEY} from './login.service';

@Injectable({
  providedIn: 'root'
})

export class FilterService {
  loggedIn: boolean;

  constructor() {
  }


  setLoggedIn(value: boolean) {
    this.loggedIn = value;
  }

  isPermissionManagement(): boolean {
    this.setPermission();
    return localStorage.getItem("permissions").includes("PERMISSION_MANAGEMENT");
  }

  isUserManagement(): boolean {
    this.setPermission();
    return localStorage.getItem("permissions").includes("USER_MANAGEMENT");
  }

  isBugManagement(): boolean {
    this.setPermission();
    return localStorage.getItem("permissions").includes("BUG_MANAGEMENT");
  }

  isBugClose(): boolean {
    this.setPermission();
    return localStorage.getItem("permissions").includes("BUG_CLOSE");
  }

  isBugExportPdf(): boolean {
    this.setPermission();
    return localStorage.getItem("permissions").includes("BUG_EXPORT_PDF");
  }

  isAdressedUser(): boolean {
    this.setPermission();
    return localStorage.getItem("permissions").includes("ADDRESED_USER");
  }

  isReportManagement(): boolean {
    this.setPermission();
    return localStorage.getItem("permissions").includes("REPORT_MANAGEMENT");
  }

  setPermission() {
    if (localStorage.getItem("permissions") === null) {
      localStorage.setItem("permissions", "");
    }
  }

}
