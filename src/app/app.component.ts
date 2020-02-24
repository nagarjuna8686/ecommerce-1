import {
  Component
} from '@angular/core';

import { WishlistService } from './services/wishlist.service';


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

  public products: any[] = [

    {
      id: 1,
      name: "HP 255 G7",
      field: 250,
      template: "amd",
      brand: "hp",
      description: "Un bellissimo computer con 4GB di ram \n pronto a far laggare tutto testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/61WXqkBAocL._AC_UY218_ML3_.jpg'
    },
    {
      id: 2,
      name: "Acer Aspire C24-3207",
      field: 650,
      modello: "amd",
      description: "Sarai pronto a dover cambiare componenti dopo 3 mesi che bello!testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/71rQia1bIOL._AC_UY218_ML3_.jpg'
    },
    {
      id: 3,
      name: "HP-PC ProBook 450",
      field: 810,
      description: "Basta computer HP, viva DELL dai basta con sti cosi tutti rotti testo di prova fantastico testo di prova fantastico testo di prova fantastico",
      url: 'https://m.media-amazon.com/images/I/81Zeqv5X0GL._AC_UY218_ML3_.jpg'
    },
    {
      id: 4,
      name: "Notebook Microtech Pro 3",
      field: 810,
      description: "Basta computer HP, viva DELL dai basta con sti cosi tutti rotti testo di prova fantastico testo di prova fantastico testo di prova fantastico",
      url: 'https://m.media-amazon.com/images/I/71IYsgt716L._AC_UY218_ML3_.jpg'
    },
    {
      id: 5,
      name: "HP 14-ds0006nl Stream Notebook",
      field: 10,
      description: "Sarai pronto a dover cambiare componenti dopo 3 mesi che bello!testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/81FzSimwr1L._AC_UY218_ML3_.jpg'
    },
    {
      id: 6,
      name: "Lenovo Ideacentre 520",
      field: 2,
      description: "Sarai pronto a dover cambiare componenti dopo 3 mesi che bello!testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/815ksSk4a6L._AC_UY218_ML3_.jpg'
    },
    {
      id: 7,
      name: "Acer Nitro 5 AN515-54-50FQ Notebook Gaming",
      field: 420,
      description: "Sarai pronto a dover cambiare componenti dopo 3 mesi che bello!testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/81d0uTxs-xL._AC_UY218_ML3_.jpg'
    },
    {
      id: 8,
      name: "Lenovo IdeaPad S340 Notebook",
      field: 3450,
      description: "Sarai pronto a dover cambiare componenti dopo 3 mesi che bello!testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/71sRVsAw9QL._AC_UY218_ML3_.jpg'
    },
    {
      id: 9,
      name: "Microsoft Surface Laptop 3, 13",
      field: 1200,
      description: "Sarai pronto a dover cambiare componenti dopo 3 mesi che bello!testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/71jurxIClaL._AC_UY218_ML3_.jpg'
    }

  ] 

  constructor(
    public wishlistService: WishlistService
  ){

  }

  getFilteredProducts = (): any[] => {
    let filteredProducts = this.products.filter(
      product => {
        
        const t = this.term.toLowerCase();
        
        const n = product.name.toLowerCase();
        const nIncludes = n.includes(t);

        const d = product.descrizione ? product.descrizione.toLowerCase() : '';
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
