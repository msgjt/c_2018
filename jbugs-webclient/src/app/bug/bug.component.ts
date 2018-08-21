///<reference path="../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component} from "@angular/core";
import {BugService} from "../services/bug.service";

@Component({
  selector: 'app-bug',
  templateUrl: './bug.component.html',
  styleUrls: ['./bug.component.css']
})

export class BugComponent {

  constructor(private bugService: BugService){
    bugService.getAll()
  }

}
