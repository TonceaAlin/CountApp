import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private baseUrl : string = "http://localhost:8080/api"
  constructor(private httpClient: HttpClient) { }

  getAllSessions(userId: string): any{
    let queryParams = new HttpParams().append("userId", userId)
    return this.httpClient.get(`${this.baseUrl}/sessions`, {params: queryParams})
  }

}
