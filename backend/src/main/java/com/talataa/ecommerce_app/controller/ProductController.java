package com.talataa.ecommerce_app.controller;


import com.talataa.ecommerce_app.dto.productDto.RequestProductDTO;
import com.talataa.ecommerce_app.dto.productDto.RequestProductUpdateDTO;
import com.talataa.ecommerce_app.dto.productDto.ResponseProductDTO;
import com.talataa.ecommerce_app.service.imp.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseProductDTO> createProduct(
                                                            @RequestBody RequestProductDTO requestProductDTO){
            try {

                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(this.productService.createProduct(requestProductDTO));
            }catch (RuntimeException e){
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST).build();
            }

    }

    @GetMapping
    public  ResponseEntity<Iterable<ResponseProductDTO>> getAllProduct(){
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
    public ResponseEntity<ResponseProductDTO> getProductById(@PathVariable Long  id){
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

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseProductDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody RequestProductUpdateDTO requestProductUpdateDTO
    ) {
        try {
            // Llamada al servicio para actualizar el producto
            ResponseProductDTO updatedProduct = productService.updateProduct(id, requestProductUpdateDTO);
            // Devuelve el producto actualizado como respuesta
            return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
        } catch (RuntimeException e) {
            // Si ocurre un error, devuelve un 400 (BAD_REQUEST)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
