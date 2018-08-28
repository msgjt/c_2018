import {Injectable} from "@angular/core";
import {Bug} from "../types/bugs";
import {BugListHeader} from "../types/bug-list-header";

@Injectable()
export class BugSortService{

  constructor(){}

  sortBugs(data: Bug[], criteria: BugListHeader): Bug[]{
    return data.sort((a,b) =>{
      switch (criteria.name) {
        case 'title': return this.compare(a.title,b.title,criteria.direction);
        case 'version': return this.compare(a.version,b.version, criteria.direction);
        case 'fixed version': return this.compare(a.fixedVersion, b.fixedVersion, criteria.direction);
        case 'severity': return this.compare(a.severity, b.severity, criteria.direction);
        case 'status': return this.compare(a.status, b.status, criteria.direction);
        case 'assigned to': return this.compare(a.assignedTo, b.assignedTo, criteria.direction);
        case 'target date': return this.compare(new Date(a.targetDate), new Date(b.targetDate), criteria.direction);
      }
    });
  }

   compare(a, b, direction):number{
    return (a < b ? -1 : 1) * (direction === 'asc' ? 1 : -1);
  }
}

