import {Component, Input, OnInit} from '@angular/core';
import {Alert, AlertType} from "../types/alert";
import {AlertService} from "../services/alert.service";

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit {
  @Input() id: string;

  public alerts: Alert[] = [];

  constructor(private alertService: AlertService) { }

  ngOnInit() {
    this.alertService.getAlert(this.id).subscribe((alert: Alert) => {
      if (!alert.message) {
        // clear alerts when an empty alert is received
        this.alerts = [];
        return;
      }

      // add alert to array
      this.alerts.push(alert);
    });
  }
  public removeAlert(alert: Alert):void {
    this.alerts = this.alerts.filter(x => x !== alert);
  }

  public cssClass(alert: Alert):string {
    if (!alert) {
      return;
    }

    switch (alert.type) {
      case AlertType.Success:
        return 'alert alert-success';
      case AlertType.Error:
        return 'alert alert-danger';
      case AlertType.Info:
        return 'alert alert-info';
      case AlertType.Warning:
        return 'alert alert-warning';
    }
  }

}
