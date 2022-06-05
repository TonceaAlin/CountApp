import { Injectable } from '@angular/core';
import {HttpClient, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Detection} from "../domain/detection";

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
    return this.httpClient.post<Detection>(`${(this.baseUrl)}/file/count-apples`, data, {
      reportProgress: true
    })
  //   const upload$ = new HttpRequest('POST', `${(this.baseUrl)}/file/count-apples`, data, {
  //     reportProgress: true,
  //     responseType: 'json'
  //   });
  //   return this.httpClient.request<Detection>(upload$)
  }

}
