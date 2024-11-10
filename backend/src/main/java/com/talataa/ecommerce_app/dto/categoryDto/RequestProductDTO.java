package com.talataa.ecommerce_app.dto.categoryDto;

import com.talataa.ecommerce_app.model.Category;
import com.talataa.ecommerce_app.model.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductDTO {

    private String name;
    private String code;
    private String description;
    private String urlImage;
    private BigDecimal price;
    private Long userId;
    private Long categoryId;
}
