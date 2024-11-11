package com.talataa.ecommerce_app.dto.productDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductUpdateDTO {

    private Long id;
    private String name;
    private String code;
    private String description;
    private String urlImage;
    private BigDecimal price;
    private Long userId;
    private Long categoryId;

}
