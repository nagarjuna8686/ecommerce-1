import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class ProductService {

  public search: BehaviorSubject<string> = new BehaviorSubject('');

  constructor(
    public router: Router
  ) {}

  searchProduct = (term:string) => {
    if (this.router.url.indexOf('/catalog') === -1) {
      this.router.navigate(['/catalog']);
    }
    this.search.next(term);
  }
}
