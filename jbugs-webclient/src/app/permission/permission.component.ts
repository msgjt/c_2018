import {Component, OnInit} from '@angular/core';
import {PermissionService} from "../services/permission.service";
import {Permission} from "../types/permissions";
import {HttpErrorResponse} from "@angular/common/http";
import {AlertService} from "../services/alert.service";

@Component({
  selector: 'app-permission',
  templateUrl: './permission.component.html',
  styleUrls: ['./permission.component.css']
})
export class PermissionComponent implements OnInit {

  permissions: Permission[]

  constructor(private permissionService: PermissionService,private alertService:AlertService) {
    this.permissions = [];
  }


  ngOnInit() {
    this.permissionService.getAll().subscribe((response: Permission[]) => {
        response.forEach((value) => {
          this.permissions.push(value);
        })
      },
      (error: HttpErrorResponse) => {
        this.error("alerts.ERROR-SERVER");
      });
  }

  OnSubmit() {

  }

  success(message: string) {
    this.alertService.success(message);
  }

  error(message: string) {
    this.alertService.error(message);
  }

}
