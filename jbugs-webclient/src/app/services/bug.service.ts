import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Bug, BugClass} from "../types/bugs";
import {Attachment} from "../types/attachment";
import {Comment} from "../types/comments";
import {Observable} from "rxjs/internal/Observable";
import {BugFilter, IBugFilter} from "../types/bug-filter";
import {Body} from "@angular/http/src/body";
import {HistoryClass} from "../types/history";


@Injectable({
  providedIn: 'root'
})

export class BugService {
  bugs: Bug[] = [];
  bug: Bug = new BugClass();
  comments: Comment[] = [];
  baseURL = 'http://localhost:8080/jbugs/rest/bugs';
  attachementURL = 'http://localhost:8080/jbugs/rest/attachments';
  tokenHeader = localStorage.getItem("userToken");


  constructor(private http: HttpClient) {
  }

  getAll(): Bug[] {
    this.bugs = [];
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': this.tokenHeader});
    this.http.get(this.baseURL, {
      headers: reqHeader
    }).subscribe(
      (response: Bug[]) => {
        response.forEach((value) => {
          this.bugs.push(value);
        })
      }
    );
    return this.bugs;
  }

  getBugById(idBug: number): Observable<Bug> {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': this.tokenHeader});
    return this.http.get<Bug>(this.baseURL + '/' + idBug, {
      headers: reqHeader
    });
  }


  addBug(bug: Bug): Observable<any> {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': this.tokenHeader});
    var attachmentModel = JSON.stringify(bug);
    console.log(attachmentModel);
    return this.http.post(this.baseURL + '/add', attachmentModel, {
      headers: reqHeader
    });
  }

  updateBug(bug: Bug):Observable<any> {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': this.tokenHeader});
    var attachmentModel = JSON.stringify(bug);
    console.log(attachmentModel);
    return this.http.post(this.baseURL + '/update', attachmentModel, {
      headers: reqHeader
    });
  }

  getAllAttachmentsForABug(idBug: number): Attachment[] {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': this.tokenHeader});
    var attachmentsForBug: Attachment[] = [];
    this.http.get(this.attachementURL + '/' + idBug,{
      headers:reqHeader
    }).subscribe((response: Attachment[]) => {
      response.forEach((value) => {
        attachmentsForBug.push(value);
      })
    });
    return attachmentsForBug;
  }

  getAllAttachments(): Attachment[] {
    var attachmentsForBug: Attachment[] = [];
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': this.tokenHeader});
    this.http.get(this.attachementURL,{
      headers:reqHeader
    }).subscribe((response: Attachment[]) => {
      response.forEach((value) => {
        attachmentsForBug.push(value);
      })
    });
    return attachmentsForBug;
  }

  deleteAttachment(attachment: Attachment):Observable<any> {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': this.tokenHeader});
    var attachmentModel = JSON.stringify(attachment);
    console.log(attachmentModel);
    return this.http.post(this.attachementURL + '/delete', attachmentModel, {
      headers: reqHeader
    });
  }

  addAttachment(attachment: Attachment):Observable<any> {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json','Authorization': this.tokenHeader});
    var attachmentModel = JSON.stringify(attachment);
    console.log('Atasamentul trimis: ' + attachmentModel);
    return this.http.post(this.attachementURL + '/add', attachmentModel,{
      headers:reqHeader
    });
  }


  getComments(bugId: number): Comment[] {
    this.comments = [];
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': this.tokenHeader});

    this.http.get(this.baseURL + '/comments/' + bugId.toString(),{
      headers:reqHeader
    }).subscribe(
      (response: Comment[]) => {
        response.forEach((value) => this.comments.push(value));
      }
    )
    return this.comments;
  }

  addComment(comment: Comment) {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': this.tokenHeader});
    var attachmentModel = JSON.stringify(comment);
    console.log('From sevice ' + attachmentModel);
    this.http.post(this.baseURL + '/comments/add', attachmentModel, {
      headers: reqHeader
    }).subscribe();
    location.reload();
  }

  sendFile(file:Uint8Array,attachment:Attachment){
    this.http.post(this.attachementURL + '/file',file).subscribe((value)=>{
      this.addAttachment(attachment);
    });
  }


  filterBugs(filters: BugFilter[]):Observable<any>{
    return this.http.post(this.baseURL + '/filter', JSON.stringify(filters),{
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    });
  }

  addHistory(history:HistoryClass){
    console.log('History sent: ' + JSON.stringify(history));
    return this.http.post(this.baseURL + '/history/add', JSON.stringify(history),{
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    });
  }

  getHistory(): Observable<any> {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': this.tokenHeader});
    return this.http.get(this.baseURL + '/history', {
      headers: reqHeader
    })
  }

}
