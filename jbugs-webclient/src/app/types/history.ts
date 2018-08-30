import {Bug} from "./bugs";
import {User} from "./user";


export interface History{
  idHistory: number;
  bugDTO: Bug;
  modifiedDate:Date;
  afterStatus:string;
  beforeStatus:string;
  userDTO: User;
}
export class HistoryClass implements History{
  afterStatus: string;
  beforeStatus: string;
  bugDTO: Bug;
  idHistory: number;
  modifiedDate: Date;
  userDTO: User;

}
