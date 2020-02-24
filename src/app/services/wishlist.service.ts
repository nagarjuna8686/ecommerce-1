import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {

  constructor() { }

  test = () => {
    console.log('wishlist test success');
  }

}
