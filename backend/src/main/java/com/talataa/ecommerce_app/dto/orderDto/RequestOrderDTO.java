package com.talataa.ecommerce_app.dto.orderDto;


import com.talataa.ecommerce_app.model.OrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderDTO {

    private OrderState orderState;
    private Long userId;
}
