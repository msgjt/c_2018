import { Injectable } from '@angular/core';
import {Observable} from "rxjs/internal/Observable";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ChartService {

  baseURL = 'http://localhost:8080/jbugs/rest/bugs/statistics';
  tokenHeader = localStorage.getItem("userToken");
  constructor(private http: HttpClient) { }

  getAllBugsStatistics():Observable<any>{
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': this.tokenHeader});
    return this.http.get(this.baseURL + '/all',{
      headers:reqHeader
    });
  }
  getFixedBugsStatistics():Observable<any>{
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': this.tokenHeader});
    return this.http.get(this.baseURL + '/users',{
      headers:reqHeader
    });
  }
  getCreatedBugsStatistics():Observable<any>{
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': this.tokenHeader});
    return this.http.get(this.baseURL + '/created',{
      headers:reqHeader
    });
  }
}
