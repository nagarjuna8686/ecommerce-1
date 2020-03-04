import {
  Component
} from '@angular/core';
import { BusyService } from './services/busy.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {

  public spinner = false;

  constructor(
    public busy:BusyService
  ) {
    busy.events.subscribe(e => {
      this.spinner = e;
    });
  }

} 
