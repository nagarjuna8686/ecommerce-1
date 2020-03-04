import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Product } from '../classes/Product';

@Injectable({
  providedIn: 'root'
})

export class ProductService {

  public search: BehaviorSubject<string> = new BehaviorSubject('');

  constructor(
    public router: Router,
    public httpClient: HttpClient 
  ) {}

    getProduct = (productId : string) => {
      return this.httpClient.get(
        environment.apiEndpoint + 'products/productsByID' + '/' + productId,
        { headers: { 'x-token' : 'LpbYigPE4PZ0Uv9fH5fHhm5lFJbF15VpLsPEz1k98l3NX'} }
      ) as Observable<Product[]>;
    }

  getProducts = (searchTerm:string, sortField:string, sortDirection:string): Observable<Product[]> => {
    return this.httpClient.get(
      // environment.apiEndpoint + 'products/'+ searchTerm +'/'+ sortField + '/' + sortDirection + '/1/12',
      environment.apiEndpoint + 'products'
    ) as Observable<Product[]>;
  }

  // insertProducts = (productID: number,name: string,price: number,discountPrice?: number,template: string,brand: string,description: string,url: string) => {

  // }

  filterProducts = (searchTerm:string, sortField:string, sortDirection:string) : Observable<Product[]> => {
    return this.httpClient.get(
      environment.apiEndpoint + 'products/'+ searchTerm +'/'+ sortField + '/' + sortDirection ,
    ) as Observable<Product[]>;
  }

  searchProduct = (term:string) => {
    if (this.router.url.indexOf('/catalog') === -1) {
      this.router.navigate(['/catalog']);
    }
    this.search.next(term);
  }
}
