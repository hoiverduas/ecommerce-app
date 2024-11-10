package com.talataa.ecommerce_app.service;

import com.talataa.ecommerce_app.model.Product;

public interface IProductService {

    Product createProduct(Product product);
    Iterable<Product> findAllProduct();
    Product findProductById(Long id);
    Product updateUser(Product product);
    void deleteProductById(Long id);

}
