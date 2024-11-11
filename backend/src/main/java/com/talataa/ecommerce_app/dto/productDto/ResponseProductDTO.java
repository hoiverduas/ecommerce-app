package com.talataa.ecommerce_app.dto.productDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductDTO {

    private Long id;
    private String name;
    private String code;
    private String description;
    private String urlImage;
    private BigDecimal price;
    private Long userId;
    private Long categoryId;
    private LocalDateTime dateCreated;

}