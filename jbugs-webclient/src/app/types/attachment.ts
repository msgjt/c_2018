import {Bug} from "./bugs";


export interface Attachment{
  bugDTO: Bug;
  blob: Uint8Array;
  extension: string;
  name:string;
}
export class AttachmentClass implements Attachment{
  blob: Uint8Array;
  bugDTO: Bug;
  extension: string;
  name: string;

}
