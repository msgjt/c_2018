import {Injectable} from '@angular/core';
import {LSKEY} from './login.service';

@Injectable({
  providedIn: 'root'
})

export class FilterService {
  loggedIn: boolean;

  constructor() {
  }

  isLoggedIn() {
    let username = localStorage.getItem(LSKEY);
    return username ? true : false;
  }
  setLoggedIn(value: boolean) {
    this.loggedIn = value;
  }
}
