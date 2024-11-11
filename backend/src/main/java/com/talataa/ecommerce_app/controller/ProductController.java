package com.talataa.ecommerce_app.controller;


import com.talataa.ecommerce_app.dto.productDto.RequestProductDTO;
import com.talataa.ecommerce_app.dto.productDto.RequestProductUpdateDTO;
import com.talataa.ecommerce_app.dto.productDto.ResponseProductDTO;
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
    public ResponseEntity<ResponseProductDTO> createProduct(@RequestBody RequestProductDTO requestProductDTO){
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

    @PutMapping("/update")
    public ResponseEntity<ResponseProductDTO> updateProduct(@RequestBody RequestProductUpdateDTO requestProductUpdateDTO){
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.productService.updateProduct(requestProductUpdateDTO));
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
