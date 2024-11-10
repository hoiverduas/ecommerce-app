package com.talataa.ecommerce_app.controller;


import com.talataa.ecommerce_app.model.Product;
import com.talataa.ecommerce_app.service.imp.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.productService.createProduct(product));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @GetMapping
    public  ResponseEntity<Iterable<Product>> getAllProduct(){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.productService.findAllProduct());
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long  id){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.productService.findProductById(id));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.productService.updateProduct(product));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id){
        try {
            this.productService.deleteProductById(id);
            return ResponseEntity
                    .status(HttpStatus.OK).build();
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }
}
