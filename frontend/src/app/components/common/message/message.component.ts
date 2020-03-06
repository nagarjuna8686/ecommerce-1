import { MessageService, Message } from '../../../services/message.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

 

  public messages: Message[] = [];

  constructor(
    public messageService: MessageService
  ) {
    this.messageService.events.subscribe(
      e => {
        if (e) {
          this.messages.push(e);
        } else {
          this.messages = [];
        }
      }
    );
  }

  ngOnInit() { }

  getMessageIcon(message: Message) {
    switch (message.severity) {
      case ('error'): {
        return 'X';
      }
      case ('warning'): {
        return '!';
      }
      case ('success'): {
        return '!';
      }
    }
    return '?';
  }

  allMessagesAreCloseable(): boolean {
    let toRet = true;
    this.messages.forEach(v => {
      if (!v.closeable) { toRet = false; }
    });
    return toRet;
  }

  clearMessages($event: Event) {
    $event.stopPropagation();
    if (this.allMessagesAreCloseable()) {
      this.messageService.clear();
    }
  }

  stopPropagation($event: Event) {
    $event.stopPropagation();
  }
}
