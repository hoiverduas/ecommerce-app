import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = "http://localhost:8080/api/v1/products";

  constructor(private httpClient: HttpClient) {}

  getProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.apiUrl);
  }

  createProduct(productData: any): Observable<any> {
    return this.httpClient.post(`${this.apiUrl}/create`, productData, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  deleteProductById(id: number): Observable<any> {
    return this.httpClient.delete(this.apiUrl + "/delete/" + id);
  }

  getProductById(id: number): Observable<Product> {
    return this.httpClient.get<Product>(this.apiUrl + "/" + id);
  }

 

  updateProduct(id: number, productData: any): Observable<any> {
    return this.httpClient.put(`${this.apiUrl}/update/${id}`, productData, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  // updateProduct(id: number, formData: any): Observable<any> {
  //   return this.httpClient.put<Product>(`${this.apiUrl}/update/${id}`, formData);
  // }


}
