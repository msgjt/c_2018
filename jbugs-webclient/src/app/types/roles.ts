import {Permission} from "./permissions";

export interface Role {
  id: number;
  type: string;
  permissions: Permission[];
}

export class RoleRest implements Role {

  private _id: number;
  private _permissions: Permission[];
  private _type: string;


  constructor(id: number, permissions: Permission[], type: string) {
    this._id = id;
    this._permissions = permissions;
    this._type = type;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get permissions(): Permission[] {
    return this._permissions;
  }

  set permissions(value: Permission[]) {
    this._permissions = value;
  }

  get type(): string {
    return this._type;
  }

  set type(value: string) {
    this._type = value;
  }

}
