export class BugListHeader {
  private _name:string;
  private _direction:string;

  constructor(name: string, direction: string) {
    this._name = name;
    this._direction = direction
  }


  get name(): string {
    return this._name;
  }

  get direction(): string {
    return this._direction;
  }


  set direction(value: string) {
    this._direction = value;
  }
}
