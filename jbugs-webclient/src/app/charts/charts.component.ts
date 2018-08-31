import { Component, OnInit } from '@angular/core';
import {ChartService} from "../services/chart.service";
import {Chart, ChartClass, ChartItem} from "../types/chart";

@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.css']
})
export class ChartsComponent implements OnInit {


  constructor() {
  }

  ngOnInit() {
  }

}
