import { Injectable } from '@angular/core';
import { CartItem } from '../classes/CartItem';
import { Product } from '../classes/Product';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  public items: CartItem[] = [];

  constructor() { }

  isItemInCart = (item) : boolean => {


    return this.items.find(i => i.product.id === item.product.id ) != null;
   } 

  addItemsToCart = (quantity,product) => {
    const ci : CartItem= {
      quantity:quantity,
      product:product
    } 
    // se non c'è lo aggiunge
    if(!this.isItemInCart(ci))
      this.items.push(ci);
      // find ritorna l'oggetto item
    else  {
      console.log("SONO ENTRATO CAZZO!");
      this.items.find(i => i.product.id === product.id ).quantity += quantity;
    }
  }

  getTotalItemsCart = () =>{
    let supp : number = 0;
    for( let i of this.items)      
      supp += i.quantity;
    return supp ;
    
  }
  //fare un metodo che mi dice se c'è l'ho già nel carrello
  

}
