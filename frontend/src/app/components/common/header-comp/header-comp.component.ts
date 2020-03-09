import { Component, OnInit, Output, EventEmitter} from '@angular/core';
import { WishlistService } from '../../../services/wishlist.service';
import { CartService } from '../../../services/cart.service'
import { ProductService } from 'src/app/services/product.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header-comp',
  templateUrl: './header-comp.component.html',
  styleUrls: ['./header-comp.component.css']
})
export class HeaderCompComponent implements OnInit {

  constructor(
    public wishlistService: WishlistService,
    public cartService : CartService,
    public productService: ProductService,
    private router: Router
  ){

  }

  public term: string;

  clicks = 0;
  // tslint:disable-next-line: max-line-length
  
  ngOnInit(): void {}

  search = () => {
    this.productService.searchProduct(this.term);
  }
  esterEgg = () => {
    this.clicks += 1;

    if(this.clicks===5){
      alert('Oh ma cè la fai a cliccare stupido ');
      this.router.navigateByUrl('/scemoscemoscemo');
      this.clicks = 0;
    }

  }

}
