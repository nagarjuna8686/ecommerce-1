import { Injectable } from '@angular/core';
import { CartItem } from '../classes/CartItem';
import { Product } from '../classes/Product';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  public items: CartItem[] = [];
  
  constructor() { }

  isItemInCart= (item) : boolean => {

    return this.items.find(i => i.product.id === item.product.id) != null;
   } 

  getItems = () =>{
    return this.items;
  }

  getTotalItemsCart = ()  =>{
    return this.items.length;
  }

  addItemsToCart = (quantity,product) => {
    let ci:CartItem= {
      quantity:quantity,
      product:product
    }
    
    this.items.push(ci);
      
  } 
}


