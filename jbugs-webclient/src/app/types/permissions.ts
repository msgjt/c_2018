export interface Permission {
  id: number;
  type: string;
  description: string;
}

export class PermissionRest implements Permission {
  description: string;
  id: number;
  type: string;
}
