import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {LoginModule} from './login/login.module';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import {FormsModule} from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import { PermissionComponent } from './permission/permission.component';
import { RoleComponent } from './role/role.component';
import { AddBugComponentComponent } from './add-bug-component/add-bug-component.component';
import {ViewBugsComponent} from "./viewBugs/viewBugs.component";


const appRoutes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/login'},
  {path: 'permission', component:PermissionComponent},
  {path: 'role', component:RoleComponent},
  {path: 'bug', component:ViewBugsComponent},
  {path: 'bug/add', component:AddBugComponentComponent},

]
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PermissionComponent,
    RoleComponent,
    AddBugComponentComponent,
    RoleComponent,
    ViewBugsComponent
  ],
  imports: [
    BrowserModule,
    LoginModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
