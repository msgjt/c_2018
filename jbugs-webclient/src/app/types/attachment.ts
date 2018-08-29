import {Bug} from "./bugs";


export interface Attachment{
  bugDTO: Bug;
  blob: Uint8Array;
  extension: string;
  name:string;
}
