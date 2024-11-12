import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../common/product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  products:Product[] = [];

  constructor(private productService:ProductService){}

  ngOnInit(): void {
    this.productService.getProducts()
    .subscribe( data => this.products = data);
   
  }



}
