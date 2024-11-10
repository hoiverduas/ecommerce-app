package com.talataa.ecommerce_app.repository;

import com.talataa.ecommerce_app.model.Order;
import com.talataa.ecommerce_app.model.OrderState;
import com.talataa.ecommerce_app.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface IOrderRepository extends CrudRepository<Order,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity o SET o.orderState = :state WHERE o.id = :id")
    void updateStateById(Long id, OrderState state);

    Iterable<Order> findByUserEntity(User user);

}
