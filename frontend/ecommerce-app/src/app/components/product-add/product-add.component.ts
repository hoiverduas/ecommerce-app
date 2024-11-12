import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css'] // Corregido `styleUrl` a `styleUrls`
})
export class ProductAddComponent implements OnInit {

  id: number = 0;
  code: string = '001';
  name: string = '';
  description: string = '';
  price: number = 0;
  urlImage: string = '';
  userId: string = '1';
  categoryId: string = '1';

  constructor(
    private productService: ProductService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.getProductById();
  }

  addOrUpdateProduct() {
    const productData = {
      id: this.id,
      code: this.code,
      name: this.name,
      description: this.description,
      price: this.price,
      urlImage: this.urlImage,
      userId: this.userId,
      categoryId: this.categoryId
    };
  
    if (this.id && this.id !== 0) {
      // Actualizar producto
      this.productService.updateProduct(this.id, productData).subscribe({
        next: (data) => {
          console.log('Producto actualizado:', data);
          this.router.navigate(['admin/product']);
        },
        error: (error) => {
          console.error('Error al actualizar el producto:', error);
        }
      });
    } else {
      // Crear producto
      this.productService.createProduct(productData).subscribe({
        next: (data) => {
          console.log('Producto creado:', data);
          this.router.navigate(['admin/product']);
        },
        error: (error) => {
          console.error('Error al crear el producto:', error);
        }
      });
    }
  }
  

  getProductById() {
    this.activatedRoute.params.subscribe(
      params => {
        const id = params['id'];
        if (id) {
          console.log('El valor de la variable id es:', id);
          this.productService.getProductById(id).subscribe(
            data => {
              // Asignar los datos del producto existente
              this.id = data.id;
              this.code = data.code;
              this.name = data.name;
              this.description = data.description;
              this.urlImage = data.urlImage;
              this.price = data.price;
              this.userId = data.userId;
              this.categoryId = data.categoryId;
            },
            error => {
              console.error('Error al obtener el producto:', error);
            }
          );
        }
      }
    );
  }
}
