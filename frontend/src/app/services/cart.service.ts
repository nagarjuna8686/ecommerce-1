import { Injectable } from '@angular/core';
import { CartItem } from '../classes/CartItem';
import { Product } from '../classes/Product';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { BehaviorSubject, Observable, forkJoin } from 'rxjs';
import { map } from 'rxjs/operators'
import { ProductService } from './product.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  public items: CartItem[] = [];
  
  constructor(
    public httpClient: HttpClient,
    public productService: ProductService
  ) { 
    this.updateCartInfo()
  }

  isItemInCart= (item) : boolean => {

    return this.items.find(i => i.product.productID === item.product.productID) != null;
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
      this.items.find(i => i.product.productID === product.productID ).quantity += quantity;
  }

  getTotalItemsCart = () =>{
    let supp : number = 0;
    for( let i of this.items)      
      supp += i.quantity;
    return supp ;
    
  }

  removeItemFromCart = (item : CartItem) => {
    this.items = this.items.filter(i => i.product.productID !== item.product.productID ) ;
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
  

  loadCart = () : Observable<CartItem[]> => {
    return this.httpClient.get(
      environment.apiEndpoint + 'cart',
      { headers: { 'x-token' : 'LpbYigPE4PZ0Uv9fH5fHhm5lFJbF15VpLsPEz1k98l3NX'} }
    ) as Observable<CartItem[]>;
  }

  updateCartInfo = () => {
    this.loadCart().subscribe( items => {
      let calls = [];
      items.forEach( (item, index) => {
          calls.push(this.productService.getProduct(item['productID'])); 
        }
      );
      forkJoin(calls).subscribe( products  => {
        products.forEach( (product, index) => {
          items[index]['product'] = product[0] as Product;
          items[index]['quantity'] = 1; 
        })
        this.items = items;
        console.log('CartItems', this.items);
      })
    })
  }

  insertIntoCart = (idUser : number, idItem : number) => {
    return this.httpClient.post(
      environment.apiEndpoint + 'cart/'+ idUser +'/'+ idItem ,
      //environment.apiEndpoint + 'cart/4/'+ idItem ,
      {idUser: idUser, idItem: idItem},
      {headers: {'Content Type': 'appplication/json'}}
    )
    this.updateCartInfo();
  }

}

