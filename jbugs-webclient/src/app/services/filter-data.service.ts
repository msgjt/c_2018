import {Injectable} from "@angular/core";
import {BugFilterChoice} from "../types/bug-filter-options";
import {BugFilter} from "../types/bug-filter";

@Injectable()
export class FilterDataService{
  private _filterList: BugFilter[] = [];
  private _chosenFilter: BugFilterChoice = new BugFilterChoice();


  get filterList(): BugFilter[] {
    return this._filterList;
  }

  set filterList(value: BugFilter[]) {
    this._filterList = value;
  }


  get chosenFilter(): BugFilterChoice {
    return this._chosenFilter;
  }

  set chosenFilter(value: BugFilterChoice) {
    this._chosenFilter = value;
  }
}
