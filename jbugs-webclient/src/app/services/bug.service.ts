import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

export interface Bug {
  id: number;
  title: string;
  description: string;
  version: string;
  targetDate: Date;
  status: string;
  fixedVersion: string;
  severity: string;
  createdBy: string;
  assignedTo: string;
}

export class BugRest implements Bug {
  private _assignedTo: string;
  private _createdBy: string;
  private _description: string;
  private _fixedVersion: string;
  private _id: number;
  private _severity: string;
  private _status: string;
  private _targetDate: Date;
  private _title: string;
  private _version: string;


  constructor(assignedTo: string, createdBy: string, description: string, fixedVersion: string, id: number, severity: string, status: string, targetDate: Date, title: string, version: string) {
    this._assignedTo = assignedTo;
    this._createdBy = createdBy;
    this._description = description;
    this._fixedVersion = fixedVersion;
    this._id = id;
    this._severity = severity;
    this._status = status;
    this._targetDate = targetDate;
    this._title = title;
    this._version = version;
  }


  get assignedTo(): string {
    return this._assignedTo;
  }

  set assignedTo(value: string) {
    this._assignedTo = value;
  }

  get createdBy(): string {
    return this._createdBy;
  }

  set createdBy(value: string) {
    this._createdBy = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get fixedVersion(): string {
    return this._fixedVersion;
  }

  set fixedVersion(value: string) {
    this._fixedVersion = value;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get severity(): string {
    return this._severity;
  }

  set severity(value: string) {
    this._severity = value;
  }

  get status(): string {
    return this._status;
  }

  set status(value: string) {
    this._status = value;
  }

  get targetDate(): Date {
    return this._targetDate;
  }

  set targetDate(value: Date) {
    this._targetDate = value;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

  get version(): string {
    return this._version;
  }

  set version(value: string) {
    this._version = value;
  }
}

@Injectable({
  providedIn: 'root'
})

export class BugService {
  bugs: Bug[] = [];
  baseURL = 'http://localhost:8080/jbugs/rest/bugs';

  constructor(private http: HttpClient) {
  }

  getAll(): void{
    this.bugs = [];
    this.http.get( this.baseURL).subscribe(
      data => {
        console.log(data);
      }
    );
  }
}
