package com.talataa.ecommerce_app.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @Enumerated(value = EnumType.STRING)
    private OrderState orderState;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderProduct> orderProducts;

    public Order(){
        orderProducts = new ArrayList<>();
    }

   public BigDecimal getTotalOrderPrice(){
        return orderProducts.stream()
                .map(orderProduct -> orderProduct.getTotalItem())
                .reduce(BigDecimal.ZERO,BigDecimal::add);
   }


}
