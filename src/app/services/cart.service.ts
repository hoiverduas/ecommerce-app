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
    // Verificar si el producto ya existe en el carrito
    if (this.items.has(itemCart.productId)) {
      // Si existe, actualizar la cantidad y el precio total
      let existingItem = this.items.get(itemCart.productId);
      if (existingItem) {
        existingItem.quantity += itemCart.quantity; // Aumentar la cantidad
      }
    } else {
      // Si no existe, añadirlo como un nuevo producto en el carrito
      this.items.set(itemCart.productId, itemCart);
    }
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

  convertToListFromMap(){
    this.itemList.splice(0,this.itemList.length);
    this.items.forEach(
      (item,clave)=>{
      this.itemList.push(item)
    }
  );

  return this.itemList;
  }
}
