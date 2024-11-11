package com.talataa.ecommerce_app.service;

import com.talataa.ecommerce_app.dto.orderDto.RequestOrderDTO;
import com.talataa.ecommerce_app.dto.orderDto.RequestUpdatedOrderDTO;
import com.talataa.ecommerce_app.dto.orderDto.ResponseOrderDTO;
import com.talataa.ecommerce_app.model.Order;
import com.talataa.ecommerce_app.model.User;

public interface IOrderService {

    ResponseOrderDTO createOrder(RequestOrderDTO requestOrderDTO);
    Iterable<ResponseOrderDTO> findAllOrder();
    ResponseOrderDTO findOrderById(Long id);
    ResponseOrderDTO updateOrder(RequestUpdatedOrderDTO requestUpdatedOrderDTO);
    void deleteOrderById(Long id);
    void updateStatusById(Long id, String status);
    Iterable<ResponseOrderDTO> findByUser(Long id);


}
