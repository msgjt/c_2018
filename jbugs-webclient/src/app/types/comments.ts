import {Bug} from "./bugs";

export interface Comment {
  id: number;
  user: string;
  text: string;
  date: Date;
  bugDTO: Bug;
}
export class CommentClass implements Comment{
  bugDTO: Bug;
  date: Date;
  id: number;
  text: string;
  user: string;

}
