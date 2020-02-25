import { Injectable } from '@angular/core';
import { CartItem } from '../classes/CartItem';
import { Product } from '../classes/Product';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  public items: CartItem[] = [];

  constructor() { }

  addItemsToCart = (quantity,product) => {
    let ci:CartItem= {
      quantity:quantity,
      product:product
    } 
     this.items.push(ci);
 
  }

  getTotalItemsCart = ()  =>{
    
    console.log(this.items.length);
    return this.items.length;
    
  }
  //fare un metodo che mi dice se c'è l'ho già nel carrello
  

}
