import { Injectable } from '@angular/core';
import { Product } from '../classes/Product';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {


  public products: Product[] = [];

  constructor() { }

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

  toogleItemInWishlist = (product) =>{
    if(this.isItemInWishlist(product)){
      this.removeItemToWishlist(product);
    }else{
      this.addItemToWishlist(product);
    }
  }

  removeItemToWishlist = (products)=> {
    this.products = this.products.filter(i => i.productID !== products.productID ) ;

  }

  
}
