
import {OrderProducts } from "./order-ptoduct";
import { orderState } from "./order-status";

export class Order {

    constructor(public id:number|null,
        public dateCreated:Date,
        public orderProducts:OrderProducts[],
        public userId:number,
        public orderState:orderState

    ){}
    getTotal(): number {
        let total = 0;
        for (let orderProduct of this.orderProducts) {
            total += orderProduct.price * orderProduct.quantity;
        }
        console.log('Total : ' + total);
        return total; // Ahora retorna el total calculado
    }
    
}
