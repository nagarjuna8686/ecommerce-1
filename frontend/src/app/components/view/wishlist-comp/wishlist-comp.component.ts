import { Component,Input, OnInit, Output } from '@angular/core';
import { WishlistService } from 'src/app/services/wishlist.service';
import { Product } from 'src/app/classes/Product';
import { ProductService } from 'src/app/services/product.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-wishlist-comp',
  templateUrl: './wishlist-comp.component.html',
  styleUrls: ['./wishlist-comp.component.css']
})
export class WishlistCompComponent implements OnInit {

  //@Input() inputProduct : Product;
  constructor(
    public cartService : CartService,
    public wishlistService: WishlistService
  ) { }

  ngOnInit(): void {
  }

}
