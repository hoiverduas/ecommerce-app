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
    this.grtProductById();
  }
   constructor(private productService:ProductService,private activetedRoute:ActivatedRoute){}
     grtProductById(){
      this.activetedRoute.params.subscribe(
        p=>{ 
          let id = p['id']
           if(id){
            this.productService.getProductById(id).subscribe(
                data=>{
                  this.id = data.id,
                  this.name = data.name;
                  this.description = data.description;
                  this.price = data.price;
                  this.urlImage = data.urlImage;
                }
            );
           }
        }
      );
    }
   
    addCart(id:number){
      console.log('id product :'+id);
      console.log('id name :'+this.name);
      console.log('id price:'+this.price);
      console.log('id quantity :'+this.quantity);



    }

  }
