import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-summary-list-comp',
  templateUrl: './summary-list-comp.component.html',
  styleUrls: ['./summary-list-comp.component.css']
})
export class SummaryListCompComponent implements OnInit {

  constructor(
    public cartService : CartService
  ) { 
    
  }

  ngOnInit(): void {
  }

}
