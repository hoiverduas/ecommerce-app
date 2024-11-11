package com.talataa.ecommerce_app.dto.orderProductDto;

import java.math.BigDecimal;

public class RequestOrderProductDTO {

    private Integer productId; // ID del producto
    private BigDecimal quantity; // Cantidad de este producto en la orden
    private BigDecimal price; // Precio del producto
}
