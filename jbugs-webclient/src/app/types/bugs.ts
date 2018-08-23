import {User} from "./user";
import {Attachment} from "./attachment";

export interface Bug {
  idBug: number;
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


