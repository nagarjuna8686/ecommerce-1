import {
  Component
} from '@angular/core';
import { BusyService } from './services/busy.service';
import { MessageService } from './services/message.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {

  public spinner = false;

  constructor(
    public busy: BusyService,
    public messageService: MessageService
  ) {
    busy.events.subscribe(e => {
      this.spinner = e;
    });

    messageService.success('SUCCESSO', "Tutto caricato con successo")
  }

} 
