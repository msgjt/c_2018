import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginComponent} from './login.component';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {TranslateModule} from "@ngx-translate/core";
import {RecaptchaFormsModule} from "ng-recaptcha/forms";
import {RECAPTCHA_LANGUAGE, RecaptchaModule} from "ng-recaptcha";
import {BugDataService} from "../services/bugData.service";

const loginRoutes: Routes = [
  {path: 'login', component: LoginComponent}
]

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(loginRoutes),
    TranslateModule,
    RecaptchaFormsModule,
    RecaptchaModule.forRoot()
  ],
  declarations: [LoginComponent],
  exports: [LoginComponent, RouterModule],
  providers: [BugDataService, {
    provide: RECAPTCHA_LANGUAGE,
    useValue: 'ro'
  }],
})
export class LoginModule {
}
