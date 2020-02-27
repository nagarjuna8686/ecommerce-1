import {
  Component
} from '@angular/core';

import { WishlistService } from './services/wishlist.service';
import { CartService } from './services/cart.service'
import { Product } from './classes/Product';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})



export class AppComponent {

  public term: string = '';



  constructor(
  ){

  }

} //fine totale
