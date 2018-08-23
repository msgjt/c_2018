export interface Permission {

  id: number;
  type: string;
  description: string;
}

export class PermissionRest implements Permission {

  private _id: number;
  private _type: string;
  private _description: string;

  constructor(id: number, type: string, description: string, rolesList: string[]) {
    this._id = id;
    this._type = type;
    this._description = description;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  set type(value: string) {
    this._type = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

}
