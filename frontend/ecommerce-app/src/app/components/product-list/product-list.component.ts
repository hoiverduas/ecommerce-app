import { Component, OnInit } from '@angular/core';
import { Product } from '../../common/product';
import { ProductService } from '../../services/product.service';
import { Subscriber } from 'rxjs';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit {
 
  products:Product[] = [];
 
  constructor(private productService:ProductService){}

  ngOnInit(): void {
    this.listProducts();
  }

  listProducts(){
    this.productService.getProducts().subscribe(
      data => {this.products = data 
        console.log(data);
      }
    );
  }

deleteProductById(id:number){

  this.productService.deleteProductById(id).subscribe(
    ()=> this.listProducts()
  );
}

}
