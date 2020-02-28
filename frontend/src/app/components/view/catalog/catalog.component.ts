import { Component, OnInit,Input, Output } from '@angular/core';
import { Product } from 'src/app/classes/Product';
import { WishlistService } from 'src/app/services/wishlist.service';
import { CartService } from 'src/app/services/cart.service';
import { EventEmitter } from 'protractor';

import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})

export class CatalogComponent implements OnInit {

  public term: string = '';

  public selected: { name:string, field:string, ascending: boolean };

  public sortOptions: any[] = [
    {
      label: "Prezzo Decrescente", 
      value: { 
        field: 'price',
        ascending: false
      }
    },
     {
       label: "Prezzo Crescente",
       value: {
         field: 'price',
         ascending: true
       }
     },
     {
       label: "Ordine Alfabetico Decrescente",
       value: {
         field: 'name',
         ascending: false
       }
     }
  ]

  public products: Product[] = [

    {
      id: 1,
      name: "Alienware 17",
      discountPrice: 4021,
      price: 9018,
      model: "intel",
      brand: "alienware",
      description: "R4 2.9GHz i7-7820HK Intel® Core™ i7 di settima generazione 17.3 2560 x 1440 Pixel Nero, Argento Computer portatile",
      url: 'https://m.media-amazon.com/images/I/81DiOTuL4vL._AC_UY327_FMwebp_QL65_.jpg'
    },
    {
      id: 2,
      name: "Asus ROG STRIX B450-F GAMING",
      price: 950,
      model: "msi",
      brand: "asus",
      description: "Scheda Madre da Gioco con Supporto DDR4 a 3200 MHz, SATA 6 Gbps, HDMI 2.0, Doppia NVMe M.2, USB 3.1, Nero",
      url: 'https://images-eu.ssl-images-amazon.com/images/I/51nydcx-hVL._AC_SY200_.jpg'
    },
    {
      id: 10,
      name: "DJI Ryze Tello Mini Drone ",
      price: 89.99,
      model: "minidrone",
      brand: "dji",
      description: "Ottimo per Creare Video con EZ Shots, Occhiali VR e Compatibilità con Controller di Gioco, Trasmissione HD a 720p e Raggio di 10",
      url: 'https://images-eu.ssl-images-amazon.com/images/I/41JDK1EKRqL._AC_US300_FMwebp_QL65_.jpg'
    },
    {
      id: 3,
      name: "HP - PC Pavilion 15",
      price: 750,
      discountPrice: 650,
      model: "amd",
      brand: "hp",
      description: "Un bellissimo computer con 4GB di ram \n pronto a far laggare tutto testo di prova fantastico testo di prova fantastico ",
      url: 'https://m.media-amazon.com/images/I/417eOIvJS7L._AC_UY327_FMwebp_QL65_.jpg'
    },
    
    {
      id: 4,
      name: "Lenovo Ideapad S130 Notebook, Display 14",
      price: 250,
      discountPrice: 199,
      model: "intel",
      brand: "lenovo",
      description: " HD, Processore Intel N5000, 128 GB SSD, RAM 4 GB, Windows 10, Midnight Blue, RAM 4 GB, Windows 10, Midnight Blue",
      url: 'https://m.media-amazon.com/images/I/71+kX8rAB7L._AC_UY327_FMwebp_QL65_.jpg'
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
    public cartService : CartService,
    public productService : ProductService
  ) { }

  ngOnInit(): void {
    this.productService.search.subscribe( term => {
      this.term = term;
    })
  }

  getFilteredProducts = (): Product[] => {

 
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
    this.products.sort( (a, b) => { //qui ho messo l'input del event
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

}
