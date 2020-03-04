import { BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';

export class Message {
  severity: string;
  header: string;
  body: string;
  closeable: boolean;
  buttons?: MessageButton[];
}

export class MessageButton {
  label: string;
  action: (a?: any) => any;
  args?: any;
  class?: string;
}

@Injectable()
export class MessageService {

  popupMessage: string;

  public events: BehaviorSubject<Message> = new BehaviorSubject(null);

  constructor() { }

  success(header: string, body: string, closeable = true) {
    this.events.next({ severity: 'success', header: header, body: body, closeable: closeable });
  }

  error(header: string, error, closeable = true) {
    let body;
    if (typeof error === 'string') {
      body = error;
    } else if (error['status'] !== undefined && error['error'] !== undefined && error.message !== undefined) {
      body = error['status'] + ' - ' + error['error'] + ': ' + error.message;
    } else {
      body = error.message;
    }
    this.events.next({ severity: 'error', header: header, body: body, closeable: closeable });
  }

  prompt(header: string, body: string, buttons: MessageButton[]) {
    this.events.next({
      severity: 'info',
      header: header,
      body: body,
      closeable: false,
      buttons: buttons
    });
  }

  clear() {
    this.events.next(null);
  }

  wip() {
    this.events.next({
      severity: 'info',
      header: 'Funzionalità non implementata',
      body: 'Questa funzionalità è attualmente in work in progress e non è stata ancora implementata.',
      closeable: true
    });
  }
}
