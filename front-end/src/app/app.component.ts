import { Component } from '@angular/core';
import {Prediction} from "../model/prediction";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'count-app';
  counts : Prediction[] = []

  setCounts(counts: Prediction[]){
    this.counts = counts;
    console.table(this.counts)
  }
}
