package com.talataa.ecommerce_app.service;

import com.talataa.ecommerce_app.model.Order;

public interface IOrderService {

    Order createOrder(Order order);
    Iterable<Order> findAllOrder();
    Order findOrderById(Long id);
    Order updateOrder(Order order);
    void deleteOrderById(Long id);
    void updateStatusById(Long id, String status);

}
