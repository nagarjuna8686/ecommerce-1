import { Injectable } from '@angular/core';
import { Product } from '../classes/Product';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { BehaviorSubject, Observable, forkJoin } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {


  public products: Product[] = [];

  constructor(
    public httpClient: HttpClient
  ) { }

  getItems = ():any[] => {
    return this.products;
  }

  getTotalItems = () => {
    return this.products.length;
  }

  addItemToWishlist = (product) => {
    console.log('ho addato');
    this.products.push(product);
  }

  isItemInWishlist = (product) : boolean => {

   return this.products.find(i => i.productID === product.productID) != null;
  } 
  

  removeItemToWishlist = (products)=> {
    this.products = this.products.filter(i => i.productID !== products.productID ) ;

  }

  loadWishlist = () =>{
    return this.httpClient.get(
      environment.apiEndpoint + 'wishlist',
      { headers: { 'x-token' : 'LpbYigPE4PZ0Uv9fH5fHhm5lFJbF15VpLsPEz1k98l3NX'} }
    ) as Observable<Product[]>;
  }

  removeItemFromWishlist = (product) => {
    return this.httpClient.request(
      'DELETE',
      environment.apiEndpoint + 'cart/nonancoradisp/'+ product ,
      {headers: {'Content-Type': 'application/json'},
         body:{ product : product}
      })
  }

  addItemIntoWishlist = (product) => {
    return this.httpClient.post(
      environment.apiEndpoint + 'cart/nonancoradisp/'+ product ,
      {product : product},
      {headers: {'Content-Type': 'application/json'}
  })
}

toogleItemInWishlist = (product) =>{
  /*
  if(this.isItemInWishlist(product)){
    this.removeItemToWishlist(product);
  }else{
    this.addItemToWishlist(product);
  }*/
  if(this.isItemInWishlist(product)){
    this.removeItemFromWishlist(product);
  }else{
    this.addItemIntoWishlist(product);
  }
}
  
}
