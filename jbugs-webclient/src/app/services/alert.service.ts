import {Injectable} from '@angular/core';
import {Subject} from "rxjs/internal/Subject";
import {Alert, AlertType} from "../types/alert";
import {NavigationStart, Router} from "@angular/router";
import {Observable} from "rxjs/internal/Observable";
import {timer} from "rxjs/internal/observable/timer";
import { interval } from 'rxjs';
import { map } from 'rxjs/operators'


@Injectable({
  providedIn: 'root'
})
export class AlertService {
  private subject = new Subject<Alert>();
  private keepAfterRouteChange = false;
  constructor(private router: Router) { // clear alert messages on route change unless 'keepAfterRouteChange' flag is true
    router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (this.keepAfterRouteChange) {
          this.keepAfterRouteChange = false;
        } else {
          // clear alert messages
          this.clear();

        }
      }
    });
  }

  // subscribe to alerts
  getAlert(alertId?: string): Observable<any> {
    return this.subject.asObservable();

  }

  // convenience methods
  success(message: string) {
    this.alert(new Alert({message, type: AlertType.Success}));

  }

  error(message: string) {
    this.alert(new Alert({message, type: AlertType.Error}));
  }

  info(message: string) {
    this.alert(new Alert({message, type: AlertType.Info}));
  }

  warn(message: string) {
    this.alert(new Alert({message, type: AlertType.Warning}));
  }

  // main alert method
  alert(alert: Alert) {
    this.keepAfterRouteChange = alert.keepAfterRouteChange;
    this.subject.next(alert);
    setTimeout(()=>{
      this.clear();
    }, 4000);

  }

  // clear alerts
  clear(alertId?: string) {
    this.subject.next(new Alert({alertId}));
  }

  closeMessage(alertId?: string){
    setTimeout(this.clear(alertId),2000);
  }


}
