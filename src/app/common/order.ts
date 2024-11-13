import { Data } from "popper.js";
import { OrderPtoduct } from "./order-ptoduct";
import { OrderStatus } from "./order-status";

export class Order {

    constructor(public id:number|null,
        public dateCreated:Date,
        public orderProduct:OrderPtoduct[],
        public userId:number,
        public orderState:OrderStatus

    ){}

    getTotal(){
        let total = 0;
        for(let orderProduct of this.orderProduct){
             total += orderProduct.price*orderProduct.quantity;
             console.log('Total :'+ total);
        }
    }
}
