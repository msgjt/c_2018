import {Bug} from "./bugs";
import * as bytes from "bytes";

export interface Attachment{
  bug: Bug;
  blob: string;
}

export class AttachmentRest implements Attachment{

  private _bug:Bug;
  private _blob:string;


  get bug(): Bug {
    return this._bug;
  }

  set bug(value: Bug) {
    this._bug = value;
  }

  get blob(): string {
    return this._blob;
  }

  set blob(value: string) {
    this._blob = value;
  }

  constructor() {
  }
}
