package com.talataa.ecommerce_app.controller;

import com.talataa.ecommerce_app.model.Category;
import com.talataa.ecommerce_app.service.imp.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.categoryService.createCategory(category));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Category>> getAllCategory(){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.categoryService.findAllCategory());
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id ){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.categoryService.findCategoryById(id));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Category> updatedCategory(@RequestBody Category category){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.categoryService.updateCategory(category));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable Long id){
        try {
            this.categoryService.deleteCategoryById(id);
            return ResponseEntity
                    .status(HttpStatus.OK).build();
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }

}
