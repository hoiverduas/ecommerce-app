import { Component, OnInit } from '@angular/core';
import { ItemCart } from '../../common/item-cart';
import { CartService } from '../../services/cart.service';
import { UserService } from '../../services/user.service';
import {OrderProducts} from '../../common/order-ptoduct';
import { Order } from '../../common/order';
import { orderState } from '../../common/order-status';
import { OrderService } from '../../services/order.service';
import { SeasionStorageService } from '../../services/seasion-storage.service';
import { Router } from '@angular/router';

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
  orderProducts:OrderProducts[] = [];
  userId:number=1;

  constructor(private cartService:CartService,
    private userService:UserService,
    private oderService:OrderService,
    private sessionStorage:SeasionStorageService,
    private router: Router,
  
    ){}

  ngOnInit(): void {
    this.items = this.cartService.convertToListFromMap();
    this.totalCart = this.cartService.totalCart();
    this.getUserById(this.userId);
    
    
  }


  generateOrder() {
    this.items.forEach(item => {
      let orderProduct = new OrderProducts(null, item.productId, item.quantity, item.price);
      this.orderProducts.push(orderProduct);  // Llenar el array de productos
    });
  
    console.log('Productos enviados al backend:', this.orderProducts);  // Verifica si los productos están correctos
  
    let receivedDate = "2024-11-13T15:54:30.250562";
    let parsedDate = new Date(receivedDate);
  
    let order = new Order(
      null,
      parsedDate,
      this.orderProducts,  // Asegúrate de que los productos estén correctamente agregados aquí
      this.userId,
      orderState.CONFIRMED,
    );
    console.log('Order:', order);
  
    this.oderService.createOrder(order).subscribe(
      data => {
        console.log('Order creada con id:', data.id);
        this.sessionStorage.setItem('order',data);
        this.router.navigate(['/success']);
      },
      
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
