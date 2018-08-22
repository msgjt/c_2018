import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {TranslateModule} from "@ngx-translate/core";

const loginRoutes: Routes = [
  {path: 'login', component: LoginComponent}
]
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(loginRoutes),
    TranslateModule
  ],
  declarations: [LoginComponent],
  exports: [LoginComponent, RouterModule]
})
export class LoginModule { }
