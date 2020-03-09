import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'

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
import { RegisterComponent } from './components/view/register/register/register.component';
import { SpinnerInterceptor } from './interceptors/spinner-interceptor';
import { BusyService } from './services/busy.service';
import { SpinnerComponent } from './components/common/spinner/spinner.component';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { MessageComponent } from './components/common/message/message.component';
import { MessageService } from './services/message.service';
import { EasterComponent } from './components/common/easter/easter.component';


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
    WishlistCompComponent,
    RegisterComponent,
    SpinnerComponent,
    MessageComponent,
    EasterComponent
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
    CartService,
    BusyService,
    MessageService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: SpinnerInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas, far);
  }
}
