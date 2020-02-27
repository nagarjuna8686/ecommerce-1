import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CatalogComponent } from './components/view/catalog/catalog.component';
import { LoginComponent } from './components/view/login/login.component';


const routes: Routes = [
  { path:'catalog', component: CatalogComponent},
  { path: 'login', component: LoginComponent},
  { path: '', redirectTo:'catalog', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
