import { Component, OnInit, Output, EventEmitter} from '@angular/core';
import { WishlistService } from '../../../services/wishlist.service';
import { CartService } from '../../../services/cart.service'
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-header-comp',
  templateUrl: './header-comp.component.html',
  styleUrls: ['./header-comp.component.css']
})
export class HeaderCompComponent implements OnInit {

  public term:string;

  constructor(
    public wishlistService: WishlistService,
    public cartService : CartService,
    public productService: ProductService
  ){

  }

  ngOnInit(): void {}

  search = () => {
    this.productService.searchProduct(this.term);
  }

}
