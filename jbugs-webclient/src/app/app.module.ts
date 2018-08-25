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
import{BugDetailsComponent} from "./viewBugs/bugDetails/bugDetails.component";
import {BugDataService} from "./services/bugData.service";
import {UpdateBugComponent} from './update-bug/update-bug.component';
import {RECAPTCHA_LANGUAGE, RecaptchaModule} from "ng-recaptcha";
import {RecaptchaFormsModule} from "ng-recaptcha/forms";

const appRoutes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/login'},
  {path: 'permission', component: PermissionComponent},
  {path: 'role', component: RoleComponent},
  {path: 'bugDTO', component: ViewBugsComponent},
  {path: 'bug/add', component: AddBugComponentComponent},
  {path: 'bug/update', component: UpdateBugComponent},
  {path: 'bug', component: ViewBugsComponent},
  {path: 'user/add', component: CreateUserComponent},
  {path: 'user/update', component: UpdateUserComponent},
  {path: 'create-user', component: CreateUserComponent},
  {path: 'bug/details', component: BugDetailsComponent}

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
    UpdateBugComponent
  ],
  imports: [
    BrowserModule,
    LoginModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
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
