import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {


  private items: any[] = [];

  constructor() { }

  getItems = ():any[] => {
    return this.items;
  }

  getTotalItems = () => {
    return this.items.length;
  }

  addItemToWishlist = (item) => {
    console.log('ho addato');
    this.items.push(item);
  }

  isItemInWishlist = (item) : boolean => {

   return this.items.find(i => i.id === item.id) != null;
  } 

  toogleItemInWishlist = (item) =>{
    if(this.isItemInWishlist(item)){
      this.removeItemToWishlist(item);
    }else{
      this.addItemToWishlist(item);
    }
  }

  removeItemToWishlist = (item)=> {
    this.items = this.items.filter(i => i.id !== item.id ) ;

  }
}
