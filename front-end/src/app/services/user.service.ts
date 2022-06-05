import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../domain/user";
import {LoginResponse} from "../domain/responses/loginResponse";
import {RegisterModel} from "../domain/register";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url: string = "http://localhost:8080/login"
  constructor(private httpClient: HttpClient) { }

  authenticate(username:string, password:string){
    return this.httpClient.post<LoginResponse>(`${this.url}/sign-in`, new User(username, password))
  }

  register(user: RegisterModel){
    return this.httpClient.post<any>(`${this.url}/sign-up`, user);
  }
}
