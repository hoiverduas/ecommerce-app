import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = "http://localhost:8080/api/v1/products";


  constructor(private httpClient:HttpClient) {}

  getProducts():Observable<Product[]>{
      return this.httpClient
      .get<Product[]>(this.apiUrl);
  }

  createProduct(formData:any,):Observable<any>{
    return this.httpClient
    .post<Product>(this.apiUrl,formData);
  }

  deleteProductById(id:number):Observable<any>{
    return this.httpClient
    .delete(this.apiUrl + "/" +id);
  }

  getProductById(id:number):Observable<Product>{
    return this.httpClient
    .get<Product>(this.apiUrl + "/"+id);

  }

}
