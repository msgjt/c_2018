import {Injectable} from '@angular/core';
import {TOKENKEY} from './login.service';

@Injectable({
  providedIn: 'root'
})

export class FilterService {
  loggedIn: boolean;

  constructor() {
  }

  isLoggedIn() {
    let username = localStorage.getItem(TOKENKEY);
    return username ? true : false;
  }

  setLoggedIn(value: boolean) {
    this.loggedIn = value;
  }

  isPermissionManagement():boolean{
    return localStorage.getItem("permissions").includes("PERMISSION_MANAGEMENT");
  }

  isUserManagement():boolean{
    return localStorage.getItem("permissions").includes("USER_MANAGEMENT");
  }

  isBugManagement():boolean{
    return localStorage.getItem("permissions").includes("BUG_MANAGEMENT");
  }

  isBugClose():boolean{
    return localStorage.getItem("permissions").includes("BUG_CLOSE");
  }

  isBugExportPdf():boolean{
    return localStorage.getItem("permissions").includes("BUG_EXPORT_PDF");
  }

  isAdressedUser():boolean{
    return localStorage.getItem("permissions").includes("ADRESSED_USER");
  }

  isReportManagement():boolean{
    return localStorage.getItem("permissions").includes("REPORT_MANAGEMENT");
  }
}
