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
}
