import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Bug} from "../types/bugs";
import {Attachment} from "../types/attachment";
import {Comment} from "../types/comments";


@Injectable({
  providedIn: 'root'
})

export class BugService {
  bugs: Bug[] = [];
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

  addBug(attachment: Attachment) {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    var attachmentModel = JSON.stringify(attachment);
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
        console.log("intra")
        attachmentsForBug.push(value);
        console.log(attachmentsForBug.length);
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

  deleteAttachment(attachment:Attachment) {
    var reqHeader = new HttpHeaders({'Content-Type': 'application/json'});
    var attachmentModel = JSON.stringify(attachment);
    console.log(attachmentModel);
    this.http.post(this.attachementURL + '/delete', attachmentModel, {
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
}
