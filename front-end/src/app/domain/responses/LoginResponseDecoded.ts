export class LoginResponseDecoded{
  username:string;
  email:string;
  id:string;

  constructor(username: string, email: string, id: string) {
    this.username = username;
    this.email = email;
    this.id = id;
  }
}
