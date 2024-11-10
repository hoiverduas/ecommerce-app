package com.talataa.ecommerce_app.service.imp;

import com.talataa.ecommerce_app.model.Product;
import com.talataa.ecommerce_app.model.User;
import com.talataa.ecommerce_app.repository.IProductRepository;
import com.talataa.ecommerce_app.service.IProductService;

public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product createProduct(Product product) {
        return this.productRepository
                .save(product);
    }

    @Override
    public Iterable<Product> findAllProduct() {
        return this.productRepository
                .findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return this.productRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public Product updateUser(Product product) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }
}
