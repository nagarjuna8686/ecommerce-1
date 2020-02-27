import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CatalogComponent } from './components/view/catalog/catalog.component';
import { LoginComponent } from './components/view/login/login.component';
import { SummaryWishlistCompComponent } from './components/common/summary-wishlist-comp/summary-wishlist-comp.component';
import { ShoppingCartCompComponent } from './components/view/shopping-cart-comp/shopping-cart-comp.component';
import { WishlistCompComponent } from './components/view/wishlist-comp/wishlist-comp.component';


const routes: Routes = [
  { path: 'catalog', component: CatalogComponent},
  { path: 'login', component: LoginComponent},
  { path: 'sum-wishlist', component: SummaryWishlistCompComponent},
  { path: 'shopping-cart-comp', component: ShoppingCartCompComponent},
  { path: 'wishlist-comp', component: WishlistCompComponent},
  { path: '', redirectTo:'catalog', pathMatch:'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
