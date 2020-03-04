import {finalize} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest
} from '@angular/common/http';
import {BusyService} from '../services/busy.service';

@Injectable()
export class SpinnerInterceptor implements HttpInterceptor {
  private count = 0;

  constructor(public spinner: BusyService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const spinner = !req.headers.get('no-spinner');

    if (spinner) {
      this.count++;
    }

    if (this.count === 1) {
      setTimeout(() => this.spinner.show(), 0);
    }

    return next.handle(req).pipe(finalize(() => {
      if (spinner) {
        this.count--;
      }
      if (this.count === 0) {
        setTimeout(() => this.spinner.hide(), 0);
      }
    }));
  }
}
