import { Component, OnInit, Output, EventEmitter} from '@angular/core';
import { WishlistService } from '../services/wishlist.service';
import { CartService } from '../services/cart.service'

@Component({
  selector: 'app-header-comp',
  templateUrl: './header-comp.component.html',
  styleUrls: ['./header-comp.component.css']
})
export class HeaderCompComponent implements OnInit {

 

  @Output() termUpdate:EventEmitter<string> = new EventEmitter()

  public term:string;

  constructor(
    public wishlistService: WishlistService,
    public cartService : CartService
  ){

  }

  ngOnInit(): void {}

  emitTermUpdate = (t:string) => {
    this.termUpdate.emit(t);
  } 

}
