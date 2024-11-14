import { HttpBackend, HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../common/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiUrl: string = 'http://localhost:8080/api/v1/orders';
  private update: string = 'status';
  constructor(private httpClient:HttpClient) { }

  createOrder(order: Order): Observable<Order> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.httpClient.post<Order>(this.apiUrl, order, { headers });
}


  updateOrder(forData:any):Observable<any>{
    return this.httpClient.post(`${this.apiUrl}/${this.update}`,forData);
  }

  getOrderByUserId(userId:number):Observable<Order[]>{
    return this.httpClient.get<Order[]>(`${this.apiUrl}/user/order/${userId}`);

  }

  getOrderById(orderId:number):Observable<Order>{
    return this.httpClient.get<Order>(`${this.apiUrl}/${orderId}`)
  }

}
