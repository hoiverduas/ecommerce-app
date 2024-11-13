import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { ActivatedRoute } from '@angular/router';
import { ItemCart } from '../../common/item-cart';
import { CartService } from '../../services/cart.service';
import { ToastrService } from 'ngx-toastr';

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
   constructor(private productService:ProductService,private activetedRoute:ActivatedRoute,private cartService:CartService,private toastr:ToastrService){}
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


      let item = new ItemCart(id,this.name,this.quantity,this.price)
       this.cartService.addItemCart(item);
       console.log('Total carrito :')
       console.log(this.cartService.totalCart());
       
       this.toastr.success('producto añadido al carrito de compra','Carrito Compras')
    }

  }
