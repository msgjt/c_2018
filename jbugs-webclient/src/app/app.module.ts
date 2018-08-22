import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {LoginModule} from './login/login.module';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import {FormsModule} from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import { PermissionComponent } from './permission/permission.component';
import { RoleComponent } from './role/role.component';
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";


const appRoutes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/login'},
  {path: 'permission', component:PermissionComponent},
  {path: 'role', component:RoleComponent},

]

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PermissionComponent,
    RoleComponent
  ],
  imports: [
    BrowserModule,
    LoginModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    TranslateModule.forRoot({loader:{
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }})
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule {

}
