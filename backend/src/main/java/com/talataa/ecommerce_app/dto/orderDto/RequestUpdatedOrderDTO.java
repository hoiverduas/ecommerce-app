package com.talataa.ecommerce_app.dto.orderDto;

import com.talataa.ecommerce_app.dto.orderProductDto.ResponseOrderProductDTO;
import com.talataa.ecommerce_app.model.OrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdatedOrderDTO {

    private Long id;
    private LocalDateTime dateCreated; // Fecha de creación de la orden
    private OrderState orderState; // Estado actual de la orden
    private Long userId; // ID del usuario que realizó la orden
    private List<ResponseOrderProductDTO> orderProducts;

}
