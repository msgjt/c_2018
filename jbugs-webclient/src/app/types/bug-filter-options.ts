import {User} from "./user";

export class BugFilterChoice{
  private _title: string;
  private _description: string;
  private _version: string;
  private _targetDate: string;
  private _status: string;
  private _fixedVersion: string;
  private _severity: string;
  private _createdByUser: string;
  private _assignedTo: string;


  get targetDate(): string {
    return this._targetDate;
  }

  set targetDate(value: string) {
    this._targetDate = value;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get version(): string {
    return this._version;
  }

  set version(value: string) {
    this._version = value;
  }

  get status(): string {
    return this._status;
  }

  set status(value: string) {
    this._status = value;
  }

  get fixedVersion(): string {
    return this._fixedVersion;
  }

  set fixedVersion(value: string) {
    this._fixedVersion = value;
  }

  get severity(): string {
    return this._severity;
  }

  set severity(value: string) {
    this._severity = value;
  }

  get createdByUser(): string {
    return this._createdByUser;
  }

  set createdByUser(value: string) {
    this._createdByUser = value;
  }

  get assignedTo(): string {
    return this._assignedTo;
  }

  set assignedTo(value: string) {
    this._assignedTo = value;
  }
}
