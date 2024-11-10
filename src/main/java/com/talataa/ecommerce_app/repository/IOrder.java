package com.talataa.ecommerce_app.repository;

import com.talataa.ecommerce_app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IOrder extends JpaRepository<Order,Long> {

}
