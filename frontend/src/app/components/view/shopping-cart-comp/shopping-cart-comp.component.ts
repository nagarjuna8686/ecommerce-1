import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-shopping-cart-comp',
  templateUrl: './shopping-cart-comp.component.html',
  styleUrls: ['./shopping-cart-comp.component.css']
})
export class ShoppingCartCompComponent implements OnInit {

  constructor(
    public cartService : CartService
  ) { }

  ngOnInit(): void {
  }

}
