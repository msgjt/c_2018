import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

export interface Bug {
  
}

@Injectable({
  providedIn:'root'
})

export class BugService{
  baseURL = 'http://localhost:8080/jbugs/rest';

  constructor(private http: HttpClient){}

  getAll(){

  }
}
