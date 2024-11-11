package com.talataa.ecommerce_app.dto.orderProductDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderProductDTO {

    private Integer productId; // ID del producto
    private BigDecimal quantity; // Cantidad de este producto en la orden
    private BigDecimal price; // Precio del producto
}
