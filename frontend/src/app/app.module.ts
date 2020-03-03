import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { Ng2SearchPipeModule } from 'ng2-search-filter';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderCompComponent } from './components/common/header-comp/header-comp.component';
import { NavbarCompComponent } from './components/common/navbar-comp/navbar-comp.component';
import { FooterCompComponent } from './components/common/footer-comp/footer-comp.component';
import { FontAwesomeModule, FaIconLibrary } from '@fortawesome/angular-fontawesome';

import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';

import { WishlistService } from './services/wishlist.service';
import { SummaryListCompComponent } from './components/common/summary-list-comp/summary-list-comp.component';
import { CartService } from './services/cart.service';
import { ProductThumbnailCompComponent } from './components/common/product-thumbnail-comp/product-thumbnail-comp.component';
import { CatalogComponent } from './components/view/catalog/catalog.component';
import { LoginComponent } from './components/view/login/login.component';
import { SummaryWishlistCompComponent } from './components/common/summary-wishlist-comp/summary-wishlist-comp.component';
import { ShoppingCartCompComponent } from './components/view/shopping-cart-comp/shopping-cart-comp.component';
import { WishlistCompComponent } from './components/view/wishlist-comp/wishlist-comp.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderCompComponent,
    NavbarCompComponent,
    FooterCompComponent,
    SummaryListCompComponent,
    ProductThumbnailCompComponent,
    CatalogComponent,
    LoginComponent,
    SummaryWishlistCompComponent,
    ShoppingCartCompComponent,
    WishlistCompComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    Ng2SearchPipeModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    HttpClientModule
  ],
  providers: [
    WishlistService,
    CartService
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas, far);
  }
}
