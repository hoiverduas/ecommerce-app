import { Component, OnInit } from '@angular/core';
import { ItemCart } from '../../common/item-cart';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-sumary-order',
  templateUrl: './sumary-order.component.html',
  styleUrl: './sumary-order.component.css'
})


export class SumaryOrderComponent implements OnInit{

  items : ItemCart[] = [];
  totalCart : number=0;
  firstName : string='';
  lastName : string='';
  email:string='';
  address:string='';

  constructor(private cartService:CartService){}

  ngOnInit(): void {
    this.items = this.cartService.convertToListFromMap();
    this.totalCart = this.cartService.totalCart();
  }

  deleteItemCart(productId:number){
    this.cartService.deleteItemCart(productId);
    this.items =this.cartService.convertToListFromMap();
    this.totalCart = this.cartService.totalCart();
  }
  



}
