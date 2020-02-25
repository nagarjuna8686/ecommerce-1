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
  public selected: { name:string, field:string, ascending: boolean };

    //input componente

  public sortOptions: any[] = [
    {
      label: "Prezzo Decrescente", 
      value: { 
        field: 'field',
        ascending: false
      }
    },
     {
       label: "Prezzo Crescente",
       value: {
         field: 'field',
         ascending: true
       }
     },
     {
       label: "Ordine Alfabetico Decrescente",
       value: {
         name: 'name',
         ascending: false
       }
     }
  ]

  public products: Product[] = [

    {
      id: 1,
      name: "HP 255 G7",
      price: 250,
      model: "amd",
      brand: "hp",
      description: "Un bellissimo computer con 4GB di ram \n pronto a far laggare tutto testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/61WXqkBAocL._AC_UY218_ML3_.jpg'
    },
    {
      id: 2,
      name: "HP 255 G7",
      price: 250,
      model: "amd",
      brand: "hp",
      description: "Un bellissimo computer con 4GB di ram \n pronto a far laggare tutto testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/61WXqkBAocL._AC_UY218_ML3_.jpg'
    },
    {
      id: 3,
      name: "HP 255 G7",
      price: 250,
      model: "amd",
      brand: "hp",
      description: "Un bellissimo computer con 4GB di ram \n pronto a far laggare tutto testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/61WXqkBAocL._AC_UY218_ML3_.jpg'
    },
    {
      id: 4,
      name: "HP 255 G7",
      price: 250,
      model: "amd",
      brand: "hp",
      description: "Un bellissimo computer con 4GB di ram \n pronto a far laggare tutto testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/61WXqkBAocL._AC_UY218_ML3_.jpg'
    },
    {
      id: 5,
      name: "HP 255 G7",
      price: 250,
      model: "amd",
      brand: "hp",
      description: "Un bellissimo computer con 4GB di ram \n pronto a far laggare tutto testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/61WXqkBAocL._AC_UY218_ML3_.jpg'
    },
    {
      id: 6,
      name: "HP 255 G7",
      price: 250,
      model: "amd",
      brand: "hp",
      description: "Un bellissimo computer con 4GB di ram \n pronto a far laggare tutto testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/61WXqkBAocL._AC_UY218_ML3_.jpg'
    },
    {
      id: 8,
      name: "HP 255 G7",
      price: 250,
      model: "amd",
      brand: "hp",
      description: "Un bellissimo computer con 4GB di ram \n pronto a far laggare tutto testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/61WXqkBAocL._AC_UY218_ML3_.jpg'
    },
    {
      id: 9,
      name: "HP 255 G7",
      price: 250,
      model: "amd",
      brand: "hp",
      description: "Un bellissimo computer con 4GB di ram \n pronto a far laggare tutto testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/61WXqkBAocL._AC_UY218_ML3_.jpg'
    },
    

  ] 

  constructor(
    public wishlistService: WishlistService,
    public cartService : CartService
  ){

  }

  getFilteredProducts = (): any[] => {
    let filteredProducts = this.products.filter(
      product => {
        
        const t = this.term.toLowerCase();
        
        const n = product.name.toLowerCase();
        const nIncludes = n.includes(t);

        const d = product.description ? product.description.toLowerCase() : '';
        const dIncludes = d.includes(t);
      
        return nIncludes || dIncludes;
      }
    );

//////////////////

    return filteredProducts;
  }
  
  orderBy(sortOption) {

    console.log('orderBy', sortOption.field, sortOption.ascending)

    this.products.sort( (a, b) => {

      const fieldA = a[sortOption.field];
      const fieldB = b[sortOption.field];
      const asc = sortOption.ascending;

      if( fieldA > fieldB) {
        return asc ? 1 : -1;
      } else if(fieldA < fieldB ) {
        return asc ? -1 : 0;
      } else {
        return 0;
      }
      
    });
  
  }

} //fine totale
