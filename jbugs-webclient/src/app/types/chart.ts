export interface Chart{
  statisticsMap: Map<string,number>[];
}
export class ChartClass implements Chart{
  statisticsMap: Map<string,number>[];

}
export class ChartItem{
  label: string;
  value: string;


  constructor(label: string, value: string) {
    this.label = label;
    this.value = value;
  }
}
