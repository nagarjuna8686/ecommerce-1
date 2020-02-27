import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { Ng2SearchPipeModule } from 'ng2-search-filter';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderCompComponent } from './header-comp/header-comp.component';
import { BodyCompComponent } from './body-comp/body-comp.component';
import { NavbarCompComponent } from './navbar-comp/navbar-comp.component';
import { FooterCompComponent } from './footer-comp/footer-comp.component';
import { FontAwesomeModule, FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { WishlistService } from './services/wishlist.service';
import { SummaryListCompComponent } from './summary-list-comp/summary-list-comp.component';
import { CartService } from './services/cart.service';
import { ProductThumbnailCompComponent } from './product-thumbnail-comp/product-thumbnail-comp.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderCompComponent,
    BodyCompComponent,
    NavbarCompComponent,
    FooterCompComponent,
    SummaryListCompComponent,
    ProductThumbnailCompComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    Ng2SearchPipeModule,
    ReactiveFormsModule,
    FontAwesomeModule
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
