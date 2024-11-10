package com.talataa.ecommerce_app.service.imp;

import com.talataa.ecommerce_app.model.Order;
import com.talataa.ecommerce_app.model.OrderState;
import com.talataa.ecommerce_app.repository.IOrderRepository;
import com.talataa.ecommerce_app.repository.IUserRepository;
import com.talataa.ecommerce_app.service.IOrderService;

import java.time.LocalDateTime;
import java.util.Optional;

public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;

    public OrderService(IOrderRepository orderRepository, IUserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return this.orderRepository
                .save(order);
    }

    @Override
    public Iterable<Order> findAllOrder() {
        return this.orderRepository
                .findAll();
    }

    @Override
    public Order findOrderById(Long id) {
        return this.orderRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public Order updateOrder(Order order) {

        Optional<Order> optionalOrder = orderRepository.findById(order.getId());

        if (optionalOrder.isPresent()){
            Order orderExist = optionalOrder.get();

            orderExist.setOrderState(order.getOrderState());
            orderExist.setUser(order.getUser());
            orderExist.setDateCreated(LocalDateTime.now());


            return this.orderRepository.save(orderExist);
        }else {
            throw new RuntimeException("Not found");
        }
    }

    @Override
    public void deleteOrderById(Long id) {
          findOrderById(id);
          this.orderRepository.deleteById(id);
    }

    @Override
    public void updateStatusById(Long id, String status) {

        if (status.equals(OrderState.CANCELLED)){
            this.orderRepository.updateStateById(id,OrderState.CANCELLED);
        }else {
            this.orderRepository.updateStateById(id,OrderState.CONFIRMED);
        }

    }


}
