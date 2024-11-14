import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../services/order.service';
import { SeasionStorageService } from '../../services/seasion-storage.service';
import { orderState } from '../../common/order-status';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrl: './success.component.css'
})
export class SuccessComponent implements OnInit {
  
  constructor(
    private orderService:OrderService,
    private seasionStorage:SeasionStorageService,

    
  ){}
  
  ngOnInit(): void {
    console.log(this.seasionStorage.getItem('order'));
    let order = this.seasionStorage.getItem('order');

    let formData = new FormData();
    formData.append('id',order.id);
    formData.append('state',orderState.CONFIRMED.toString());

    this.orderService.updateOrder(formData).subscribe(
      data => console.log(data)
    );

  }

}
