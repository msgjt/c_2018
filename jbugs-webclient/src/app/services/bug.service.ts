import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Bug, BugClass} from "../types/bugs";
import {Attachment} from "../types/attachment";
import {Comment} from "../types/comments";
import {Observable} from "rxjs/internal/Observable";


@Injectable({
  providedIn: 'root'
})

export class BugService {
  bugs: Bug[] = [];
  bug: Bug = new BugClass();
  comments: Comment[] = [];
  baseURL = 'http://localhost:8080/jbugs/rest/bugs';
  attachementURL = 'http://localhost:8080/jbugs/rest/attachments';


  constructor(private http: HttpClient) {
  }

  getAll(): Bug[] {
    this.bugs = [];
    this.http.get(this.baseURL).subscribe(
      (response: Bug[]) => {
        response.forEach((value) => {
          this.bugs.push(value);
        })
      }
    );
    return this.bugs;
  }

  getBugById(idBug: number): Observable<Bug> {
    return this.http.get<Bug>(this.baseURL + '/' + idBug);
  }


  addBug(bug:Bug) {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    var attachmentModel = JSON.stringify(bug);
    console.log(attachmentModel);
    this.http.post(this.baseURL + '/add', attachmentModel, {
      headers: reqHeader
    }).subscribe();
  }

  updateBug(bug: Bug) {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    var attachmentModel = JSON.stringify(bug);
    console.log(attachmentModel);
    this.http.post(this.baseURL + '/update', attachmentModel, {
      headers: reqHeader
    }).subscribe();
  }

  getAllAttachmentsForABug(idBug: number): Attachment[] {
    var attachmentsForBug: Attachment[] = [];
    this.http.get(this.attachementURL + '/' + idBug).subscribe((response: Attachment[]) => {
      response.forEach((value) => {
        attachmentsForBug.push(value);
      })
    });
    return attachmentsForBug;
  }

  getAllAttachments(): Attachment[] {
    var attachmentsForBug: Attachment[] = [];
    this.http.get(this.attachementURL).subscribe((response: Attachment[]) => {
      response.forEach((value) => {
        attachmentsForBug.push(value);
      })
    });
    return attachmentsForBug;
  }

  deleteAttachment(attachment: Attachment) {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    var attachmentModel = JSON.stringify(attachment);
    console.log(attachmentModel);
    this.http.post(this.attachementURL + '/delete', attachmentModel, {
      headers: reqHeader
    }).subscribe();
  }

  addAttachment(attachment: Attachment) {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    var attachmentModel = JSON.stringify(attachment);
    console.log(attachmentModel);
    this.http.post(this.attachementURL + '/add', attachmentModel, {
      headers: reqHeader
    }).subscribe();
  }


  getComments(bugId: number): Comment[] {
    this.comments = [];
    this.http.get(this.baseURL + '/comments/' + bugId.toString()).subscribe(
      (response: Comment[]) => {
        response.forEach((value) => this.comments.push(value));
      }
    )
    return this.comments;
  }
  addComment(comment:Comment){
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    var attachmentModel = JSON.stringify(comment);
    console.log('From sevice ' + attachmentModel);
    this.http.post(this.baseURL + '/comments/add', attachmentModel, {
      headers: reqHeader
    }).subscribe();
    location.reload();
  }
}
