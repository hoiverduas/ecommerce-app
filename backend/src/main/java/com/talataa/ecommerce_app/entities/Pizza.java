package com.talataa.ecommerce_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "pizza")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Long idPizza;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "description", length = 150)
    private String description;

    @Column(name = "price", precision = 5, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "vegetarian", nullable = false)
    private Boolean vegetarian;

    @Column(name = "vegan", nullable = false)
    private Boolean vegan;

    @Column(name = "available", nullable = false)
    private Boolean available;
}
