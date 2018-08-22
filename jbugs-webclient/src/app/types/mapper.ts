import {Attachment} from "./attachment";
import {Bug} from "./bugs";

export class BugAttachmentMapper{
  public _bug: Bug;
  public _attachment: Attachment;


  constructor() {

  }


  get bug(): Bug {
    return this._bug;
  }

  set bug(value: Bug) {
    this._bug = value;
  }

  get attachment(): Attachment {
    return this._attachment;
  }

  set attachment(value: Attachment) {
    this._attachment = value;
  }
}
