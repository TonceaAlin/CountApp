import {Time} from "@angular/common";

export class Session{
  name: string;
  time: string | null;
  userId: string | null;

  constructor(name: string, time: string | null, userId: string | null) {
    this.name = name;
    this.time = time;
    this.userId = userId;
  }
}
