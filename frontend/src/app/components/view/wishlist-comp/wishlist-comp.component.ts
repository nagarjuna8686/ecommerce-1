import { Component, OnInit } from '@angular/core';
import { WishlistService } from '../../../services/wishlist.service';

@Component({
  selector: 'app-wishlist-comp',
  templateUrl: './wishlist-comp.component.html',
  styleUrls: ['./wishlist-comp.component.css']
})
export class WishlistCompComponent implements OnInit {

  constructor(
    public wishlistService: WishlistService
  ) { }

  ngOnInit(): void {
  }

}
