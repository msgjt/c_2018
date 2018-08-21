export interface Role {
  id: number;
  type: string;
  permissionsList: string[];
}

export class RoleRest implements Role {

  private _id: number;
  private _permissionsList: string[];
  private _type: string;


  constructor(id: number, permissionsList: string[], type: string) {
    this._id = id;
    this._permissionsList = permissionsList;
    this._type = type;
  }


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get permissionsList(): string[] {
    return this._permissionsList || [];
  }

  set permissionsList(value: string[]) {
    this._permissionsList = value;
  }

  get type(): string {
    return this._type;
  }

  set type(value: string) {
    this._type = value;
  }
}
