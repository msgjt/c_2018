import {Bug} from "./bugs";


export interface Attachment{
  bugDTO: Bug;
  blob: File;
  extension: string;
}
