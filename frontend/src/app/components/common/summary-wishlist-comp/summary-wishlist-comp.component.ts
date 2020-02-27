import { Component, OnInit } from '@angular/core';
import { WishlistService } from 'src/app/services/wishlist.service';

@Component({
  selector: 'app-summary-wishlist-comp',
  templateUrl: './summary-wishlist-comp.component.html',
  styleUrls: ['./summary-wishlist-comp.component.css']
})
export class SummaryWishlistCompComponent implements OnInit {

  constructor(
    public WishlistService : WishlistService
    ) {
    
   }

  ngOnInit(): void {
  }

}
