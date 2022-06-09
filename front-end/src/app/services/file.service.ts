import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Detection} from "../domain/detection";
import {Session} from "../domain/session";

@Injectable({
  providedIn: 'root'
})
export class FileService {
  private baseUrl : string = "http://localhost:8080/api"
  private results = []

  constructor(private httpClient: HttpClient) { }

  getResults(){
    return this.results
  }

  countApples(data: FormData): Observable<Detection>{
    return this.httpClient.post<Detection>(`${(this.baseUrl)}/file/count-apples`,data)
  }

  startSession(data: Session): any{
    return this.httpClient.post<any>(`${(this.baseUrl)}/start-session`, data)
  }
}
