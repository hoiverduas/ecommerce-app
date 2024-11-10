package com.talataa.ecommerce_app.repository;

import com.talataa.ecommerce_app.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product,Long> {

}
