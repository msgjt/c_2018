import {Bug} from "../types/bugs";
import {Injectable} from "@angular/core";

@Injectable()
export class BugDataService{
  private _bug: Bug;

  get bug(): Bug {
    return this._bug;
  }

  set bug(value: Bug) {
    this._bug = value;
  }
}
