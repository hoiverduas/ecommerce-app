package com.talataa.ecommerce_app.repository;

import com.talataa.ecommerce_app.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IOrderProductRepository extends JpaRepository<OrderProduct,Long> {
}
