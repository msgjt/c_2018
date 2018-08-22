import {User} from "./user";
import {Attachment} from "./attachment";

export interface Bug {
  id: number;
  title: string;
  description: string;
  version: string;
  targetDate: number;
  status: string;
  fixedVersion: string;
  severity: string;
  createdByUser: User;
  assignedTo: User;
}

export class BugRest implements Bug {
  private _assignedTo: User;
  private _createdByUser: User;
  private _description: string;
  private _fixedVersion: string;
  private _id: number;
  private _severity: string;
  private _status: string;
  private _targetDate: number;
  private _title: string;
  private _version: string;



  constructor(){

  }

  get assignedTo(): User {
    return this._assignedTo;
  }

  set assignedTo(value: User) {
    this._assignedTo = value;
  }

  get createdByUser(): User {
    return this._createdByUser;
  }

  set createdByUser(value: User) {
    this._createdByUser = value;
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

  get targetDate(): number {
    return this._targetDate;
  }

  set targetDate(value: number) {
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


