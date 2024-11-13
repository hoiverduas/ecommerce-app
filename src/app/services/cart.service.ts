import { Injectable } from '@angular/core';
import { ItemCart } from '../common/item-cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {
   
  private items: Map<number,ItemCart> = new Map<number,ItemCart>();
  itemList : ItemCart [] = []
  constructor() { }

  addItemCart(itemCart: ItemCart) {
    if (this.items.has(itemCart.productId)) {
      let existingItem = this.items.get(itemCart.productId);
      if (existingItem) {
        existingItem.quantity += itemCart.quantity;
      }
    } else {
      this.items.set(itemCart.productId, itemCart);
    }
  }
  
  convertToListFromMap() {
    this.itemList = Array.from(this.items.values());
    return this.itemList;
  }
  

  deleteItemCart(productId:number){
    this.items.delete(productId);
    this.items.forEach(
      (valor,clave) =>{
        console.log('esta es la clave y su valor :' + clave,valor);
      }
    )
  }

  totalCart(){
    let totalCart:number = 0;
    this.items.forEach(
      (item,clave)=>{
      totalCart += item.getTotalPriceItem();
    }
    );
    return totalCart
  }


}
