import { Component, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { provideHttpClient } from '@angular/common/http';
import { ProductListComponent } from './components/product-list/product-list.component';
import { HeaderAdminComponent } from './components/header-admin/header-admin.component';
import { Routes,RouterModule } from '@angular/router';
import { ProductAddComponent } from './components/product-add/product-add.component';
import { FormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { CategoryListComponent } from './components/category-list/category-list.component';
import { CategoryAddComponent } from './components/category.add/category.add.component'; 
import { DetailProductComponent } from './components/detail-product/detail-product.component';
import { HeaderUserComponent } from './components/header-user/header-user.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SumaryOrderComponent } from './components/sumary-order/sumary-order.component';
import { SuccessComponent } from './components/success/success.component';
import { RegistrationComponent } from './components/authetication/registration/registration.component'; 
import { LoginComponent } from './components/authetication/login/login.component';


const routes: Routes =[
{path:'',component:HomeComponent},
{path:'admin/product',component:ProductListComponent},
{path:'admin/product/addProduct',component:ProductAddComponent},
{path:'admin/product/update/:id',component:ProductAddComponent},
{path:'admin/category',component:CategoryListComponent},
{path:'admin/category/add',component:CategoryAddComponent},
{path:'admin/category/update/:id',component:CategoryAddComponent},
{path:'cart/detailProduct/:id',component:DetailProductComponent},
{path:'cart/sumary',component:SumaryOrderComponent},
{path:'success',component:SuccessComponent},
{path:'user/register',component:RegistrationComponent},
{path:'user/login',component:LoginComponent},



];


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProductListComponent,
    HeaderAdminComponent,
    ProductAddComponent,
    CategoryListComponent,
    CategoryAddComponent,
    DetailProductComponent,
    HeaderUserComponent,
    SumaryOrderComponent,
    SuccessComponent,
    RegistrationComponent,
    LoginComponent,
    
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    FormsModule,
    ToastrModule.forRoot(),
   
  ],
  providers: [provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }
