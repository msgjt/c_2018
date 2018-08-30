import {Role} from "./roles";

export interface User {
  firstName:string;
  lastName:string;
  username:string;
  email:string;
  password:string;
  phoneNumber:string;
  isActive: boolean;
  roles: Role[];
  fullname?: string;
}
export class UserClass implements User{
  email: string;
  firstName: string;
  fullname: string;
  isActive: boolean;
  lastName: string;
  password: string;
  phoneNumber: string;
  roles: Role[];
  username: string;

}

