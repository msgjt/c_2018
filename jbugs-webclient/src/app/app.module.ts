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
import {CreateUserComponent} from './create-user/create-user.component';
import {NgSelectModule} from '@ng-select/ng-select';
import {NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';
import {NgxPaginationModule} from "ngx-pagination";
import {UpdateUserComponent} from "./update-user/update-user.component";
import {BugDetailsComponent} from "./bugDetails/bugDetails.component";
import {BugDataService} from "./services/bugData.service";
import {UpdateBugComponent} from './update-bug/update-bug.component';
import {RECAPTCHA_LANGUAGE, RecaptchaModule} from "ng-recaptcha";
import {RecaptchaFormsModule} from "ng-recaptcha/forms";
import {BugSortService} from "./services/bug-sort.service";
import {ErrorComponent} from "./error/error.component";
import {HomeComponent} from "./home/home.component";
import {PermissionManagementGuard} from "./guards/permission-management.guard";
import {BugManagementGuard} from "./guards/bug-management.guard";
import {UserManagementGuard} from "./guards/user-management.guard";
import {AdressedUserGuard} from "./guards/adressed-user.guard";
import {NotificationDetailComponent} from "./notification-popup/notification-detail/notification-detail.component";
import {NotificationPopupComponent} from "./notification-popup/notification-popup.component";
import {ContactComponent} from './contact/contact.component';
import {AlertComponent} from './alert/alert.component';
import {FilterDataService} from "./services/filter-data.service";
import {NotificationDataService} from "./services/notification-data.service";

import {ExcelService} from "./services/excel.service";
import { ViewHistoryComponent } from './view-history/view-history.component';
import { ChartsComponent } from './charts/charts.component';
import { AllBugsChartComponent } from './charts/all-bugs-chart/all-bugs-chart.component';
import { FixedBugsChartComponent } from './charts/fixed-bugs-chart/fixed-bugs-chart.component';
import { CreatedBugsChartComponent } from './charts/created-bugs-chart/created-bugs-chart.component';
import {ChartsModule} from "ng2-charts";


const appRoutes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/login'},
  {path: 'permission', component: PermissionComponent, canActivate: [PermissionManagementGuard]},
  {path: 'role', component: RoleComponent, canActivate: [PermissionManagementGuard]},
  {path: 'bug/add', component: AddBugComponentComponent, canActivate: [BugManagementGuard]},
  {path: 'bug/update', component: UpdateBugComponent, canActivate: [BugManagementGuard]},
  {path: 'bug/details', component: BugDetailsComponent, canActivate: [BugManagementGuard]},
  {path: 'bug/viewHistory', component: ViewHistoryComponent, canActivate: [BugManagementGuard]},
  {path: 'bug/statistics', component: ChartsComponent, canActivate: [BugManagementGuard]},
  {path: 'bug/statistics/all', component: AllBugsChartComponent, canActivate: [BugManagementGuard]},
  {path: 'bug/statistics/fixed', component: FixedBugsChartComponent, canActivate: [BugManagementGuard]},
  {path: 'bug/statistics/created', component: CreatedBugsChartComponent, canActivate: [BugManagementGuard]},
  {path: 'user/add', component: CreateUserComponent, canActivate: [UserManagementGuard]},
  {path: 'user/update', component: UpdateUserComponent, canActivate: [UserManagementGuard]},
  {path: 'create-user', component: CreateUserComponent, canActivate: [UserManagementGuard]},
  {path: 'notification', component: NotificationDetailComponent, canActivate: [AdressedUserGuard]},
  {path: '', component: NotificationPopupComponent, canActivate: [AdressedUserGuard]},
  {path: 'error', component: ErrorComponent},
  {path: 'home', component: HomeComponent},
  {path: 'contact', component: ContactComponent},
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
    BugDetailsComponent,
    CreateUserComponent,
    UpdateUserComponent,
    UpdateBugComponent,
    ErrorComponent,
    HomeComponent,
    NotificationDetailComponent,
    NotificationPopupComponent,
    ContactComponent,
    AlertComponent,
    ViewHistoryComponent,
    ChartsComponent,
    AllBugsChartComponent,
    FixedBugsChartComponent,
    CreatedBugsChartComponent,
    AllBugsChartComponent,
    FixedBugsChartComponent,
    CreatedBugsChartComponent
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
    RecaptchaModule.forRoot(),
    ChartsModule

  ],

  providers: [AuthGuard, BugDataService, BugSortService, FilterDataService, ExcelService, NotificationDataService, {
    provide: RECAPTCHA_LANGUAGE,
    useValue: 'ro'
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
