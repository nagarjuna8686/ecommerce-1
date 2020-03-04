import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class BusyService {
  public events: BehaviorSubject<boolean> = new BehaviorSubject(false);

  constructor() {}

  show() {
    this.events.next(true);
  }

  hide() {
    this.events.next(false);
  }

}
