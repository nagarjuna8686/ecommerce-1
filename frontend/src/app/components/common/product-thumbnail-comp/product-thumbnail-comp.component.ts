import { Component, OnInit,Input,Output } from '@angular/core';
import { WishlistService } from 'src/app/services/wishlist.service';
import { CartService } from 'src/app/services/cart.service';
import { Product } from 'src/app/classes/Product';

@Component({
  selector: 'app-product-thumbnail-comp',
  templateUrl: './product-thumbnail-comp.component.html',
  styleUrls: ['./product-thumbnail-comp.component.css']
})

export class ProductThumbnailCompComponent implements OnInit {

  @Input() inputProduct : Product;

  constructor(
    public wishlistService: WishlistService,
    public cartService : CartService
  ) {

  }

  ngOnInit(): void {
  }

  
}
