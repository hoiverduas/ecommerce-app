package com.talataa.ecommerce_app.dto.orderDto;

import com.talataa.ecommerce_app.model.OrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOrderDTO {

    private Long id;
    private LocalDateTime dateCreated;
    private OrderState orderState;
    private Long userId;

}
