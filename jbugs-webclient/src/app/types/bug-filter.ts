export interface IBugFilter {
  field: string;
  endData: string;
  data:string;

}

export class BugFilter {
  private field: string;
  private endData: string;
  private data: string;


  constructor(field: string, endData: string, data: string) {
    this.field = field;
    this.endData = endData;
    this.data = data;
  }


}
