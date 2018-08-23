import {Bug} from "./bugs";
import * as bytes from "bytes";

export interface Attachment{
  bugDTO: Bug;
  blob: string;
}

// export class AttachmentRest implements Attachment{
//
//   private _bugDTO:Bug;
//   private _blob:string;
//
//
//   get bugDTO(): Bug {
//     return this._bugDTO;
//   }
//
//   set bugDTO(value: Bug) {
//     this._bugDTO = value;
//   }
//
//   get blob(): string {
//     return this._blob;
//   }
//
//   set blob(value: string) {
//     this._blob = value;
//   }
//
//   constructor() {
//   }
// }
