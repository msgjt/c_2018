import {Component, OnInit} from '@angular/core';
import {ChartService} from "../../services/chart.service";
import {HttpErrorResponse} from "@angular/common/http";
import {AlertService} from "../../services/alert.service";

@Component({
  selector: 'app-all-bugs-chart',
  templateUrl: './all-bugs-chart.component.html',
  styleUrls: ['./all-bugs-chart.component.css']
})
export class AllBugsChartComponent implements OnInit {

  values: number[] = [];
  finalLabels: string[] = [];
  dataMap: Map<string, number>;
  chartOptions = {responsive: true};
  colors = [
    {
      backgroundColor: 'rgba(30, 169, 224, 0.8)'
    }
  ];
  chartData = [
    {}
  ];


  constructor(private charService: ChartService, private alertService: AlertService) {


  }

  ngOnInit() {
    this.charService.getAllBugsStatistics().subscribe((value) => {
        if (value !== undefined) {
          this.dataMap = value;
          this.getItems();
          this.chartData = [{
            label: 'Bug',
            data: this.values
          }]
        }
      },
      (error: HttpErrorResponse) => {
        this.error("alerts.ERROR-SERVER");
      })
  }

  getItems() {
    for (var propertyName in this.dataMap) {
      this.values.push(this.dataMap[propertyName]);
      this.finalLabels.push(propertyName);


    }

  }

  onChartClick(event) {
    console.log(event);
  }

  public error(message: string): void {
    this.alertService.error(message);
  }

}
