import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {LoginModule} from './login/login.module';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from './auth/auth.guard';
import {FormsModule} from '@angular/forms';
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import {HeaderComponent} from './header/header.component';
import {PermissionComponent} from './permission/permission.component';
import {RoleComponent} from './role/role.component';
import {AddBugComponentComponent} from './add-bug-component/add-bug-component.component';
import {ViewBugsComponent} from "./viewBugs/viewBugs.component";
import {CreateUserComponent} from './create-user/create-user.component';
import {NgSelectModule} from '@ng-select/ng-select';
import {NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';
import {NgxPaginationModule} from "ngx-pagination";
import {UpdateUserComponent} from "./update-user/update-user.component";
import {BugDetailsComponent} from "./viewBugs/bugDetails/bugDetails.component";
import {BugDataService} from "./services/bugData.service";
import {UpdateBugComponent} from './update-bug/update-bug.component';
import {RECAPTCHA_LANGUAGE, RecaptchaModule} from "ng-recaptcha";
import {RecaptchaFormsModule} from "ng-recaptcha/forms";
import {AuthenticateGuard} from "./guards/authenticate.guard";
import { ErrorComponent } from './error/error.component';

const appRoutes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/login'},
  {path: 'permission', component: PermissionComponent, canActivate: [AuthenticateGuard]},
  {path: 'role', component: RoleComponent, canActivate: [AuthenticateGuard]},
  {path: 'bugDTO', component: ViewBugsComponent,canActivate: [AuthenticateGuard]},
  {path: 'bug/add', component: AddBugComponentComponent,canActivate: [AuthenticateGuard]},
  {path: 'bug/update', component: UpdateBugComponent,canActivate: [AuthenticateGuard]},
  {path: 'bug', component: ViewBugsComponent,canActivate: [AuthenticateGuard]},
  {path: 'user/add', component: CreateUserComponent,canActivate: [AuthenticateGuard]},
  {path: 'user/update', component: UpdateUserComponent,canActivate: [AuthenticateGuard]},
  {path: 'create-user', component: CreateUserComponent,canActivate: [AuthenticateGuard]},
  {path: '**', component: ErrorComponent}

]


export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PermissionComponent,
    RoleComponent,
    AddBugComponentComponent,
    RoleComponent,
    ViewBugsComponent,
    BugDetailsComponent,
    CreateUserComponent,
    UpdateUserComponent,
    UpdateBugComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    LoginModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes, {onSameUrlNavigation: 'reload'}),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    NgSelectModule,
    NgMultiSelectDropDownModule.forRoot(),
    NgxPaginationModule,
    RecaptchaFormsModule,
    RecaptchaModule.forRoot()
  ],
  providers: [AuthGuard, BugDataService, {
    provide: RECAPTCHA_LANGUAGE,
    useValue: 'ro'
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
