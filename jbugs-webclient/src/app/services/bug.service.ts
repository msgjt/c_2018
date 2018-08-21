import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Bug} from "../types/bugs";
import {Permission} from "../types/permissions";


@Injectable({
  providedIn: 'root'
})

export class BugService {
  bugs: Bug[] = [];
  baseURL = 'http://localhost:8080/jbugs/rest/bugs';

  constructor(private http: HttpClient) {
  }

  getAll(): Bug[]{
    this.bugs = [];
    this.http.get( this.baseURL).subscribe(
      (response: Bug[]) =>{
        response.forEach((value) => {
          this.bugs.push(value);
        })
      }
    );
    return this.bugs;
  }

  filterBySeverity(severity:string): Bug[]{
    return this.bugs.filter((item)=> item.severity == severity);
  }
}
