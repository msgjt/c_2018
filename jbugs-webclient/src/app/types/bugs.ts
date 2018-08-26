import {User} from "./user";
import {Attachment} from "./attachment";

export interface Bug {
  idBug: number;
  title: string;
  description: string;
  version: string;
  targetDate: string;
  status: string;
  fixedVersion: string;
  severity: string;
  createdByUser: User;
  assignedTo: User;
}
export class BugClass implements Bug{
  assignedTo: User;
  createdByUser: User;
  description: string;
  fixedVersion: string;
  idBug: number;
  severity: string;
  status: string;
  targetDate: string;
  title: string;
  version: string;

}

