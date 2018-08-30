import {Permission} from "./permissions";

export interface Role {
  id: number;
  type: string;
  permissions: Permission[];
}

export class RoleClass implements Role {
  id: number;
  permissions: Permission[];
  type: string;
}
