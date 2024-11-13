import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-detail-product',
  templateUrl: './detail-product.component.html',
  styleUrl: './detail-product.component.css'
})
export class DetailProductComponent implements OnInit {

  id:number=0;
  name:string='';
  description:string='';
  price:number=0;
  urlImage:string='';
  quantity:number=0;

  ngOnInit(): void {
  }
   constructor(private productService:ProductService,private activetedRoute:ActivatedRoute){}
  
   

  }
