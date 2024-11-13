import { Component, OnInit } from '@angular/core';
import { ItemCart } from '../../common/item-cart';
import { CartService } from '../../services/cart.service';
import { UserService } from '../../services/user.service';
import { OrderPtoduct } from '../../common/order-ptoduct';
import { Order } from '../../common/order';
import { OrderStatus } from '../../common/order-status';
import { OrderService } from '../../services/order.service';

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
  orderProducts:OrderPtoduct [] = [];
  userId:number=1;

  constructor(private cartService:CartService,private userService:UserService,private oderService:OrderService){}

  ngOnInit(): void {
    this.items = this.cartService.convertToListFromMap();
    this.totalCart = this.cartService.totalCart();
    this.getUserById(this.userId);
    
  }


  generateOrder(){
    this.items.forEach(
      item=>{
        let orderPtoduct = new OrderPtoduct(null,item.productId,item.quantity,item.price);
        this.orderProducts.push(orderPtoduct);
      }
    );

    let order = new Order(null,new Date(),this.orderProducts,this.userId,OrderStatus.CANCELLED);
    this.oderService.createOrder(order).subscribe(
      data =>{
        console.log('Order creada con id : '+data.id)
      }
    );
  }

  deleteItemCart(productId:number){
    this.cartService.deleteItemCart(productId);
    this.items =this.cartService.convertToListFromMap();
    this.totalCart = this.cartService.totalCart();
  }
  

  getUserById(id:number){
    this.userService.getUserById(id).subscribe(
      data =>{
        this.firstName = data.firstName,
        this.lastName = data.lastName;
        this.email = data.email;
        this.address = data.address;
      }
    );




  }


}
