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

  addItemsToCart = (quantity,product,price) => {
    const ci : CartItem= {
      quantity:quantity,
      product:product
    } 
    console.log(quantity,product,price);
    // se non c'è lo aggiunge
    if(!this.isItemInCart(ci))
      this.items.push(ci);
      // find ritorna l'oggetto item
    else  
      this.items.find(i => i.product.id === product.id ).quantity += quantity;
  }

  getTotalItemsCart = () =>{
    let supp : number = 0;
    for( let i of this.items)      
      supp += i.quantity;
    return supp ;
    
  }

  removeItemFromCart = (item : CartItem) => {
    this.items = this.items.filter(i => i.product.id !== item.product.id ) ;
  }

  incrementItemQuantityInCart = (item:CartItem) => {
    item.quantity ++;
  }

  //modifica : tolgo if e decremento a prescindere, if su una sola riga
  decrementItemQuantityInCart = (item:CartItem) => {
    if(item.quantity > 1)
       item.quantity --;     
    else if(item.quantity > 0)
      this.removeItemFromCart(item);
  }
      /*
        routing come fare pagine diverse in angular,
        pagina di login che si interfaccia, authentication service.
        angular chiamate rest e interfaccia api
      */

      getCartTotalAmount = ():number => {
        let t = 0;
        this.items.forEach( (item) =>
          {
            if(item.product.discountPrice)
              t+=item.product.discountPrice*item.quantity;
            else
              t+=item.product.price*item.quantity;
          })
        return t;
      }
  }



