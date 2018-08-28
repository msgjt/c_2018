import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {FilterService} from "../services/filter.service";

@Injectable({
  providedIn: 'root'
})
export class AdressedUserGuard implements CanActivate {
  constructor(private router: Router, private filterService: FilterService) {
  }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (localStorage.getItem('currentUser') && this.filterService.isAdressedUser()) {
      return true;
    } else {
      this.router.navigate(['/error']);
      return false;
    }
  }
}
