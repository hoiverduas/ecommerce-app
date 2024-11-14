package com.talataa.ecommerce_app.service;

import com.talataa.ecommerce_app.dto.orderDto.ResponseOrderDTO;
import com.talataa.ecommerce_app.dto.productDto.RequestProductDTO;
import com.talataa.ecommerce_app.dto.productDto.RequestProductUpdateDTO;
import com.talataa.ecommerce_app.dto.productDto.ResponseProductDTO;
import com.talataa.ecommerce_app.model.User;

public interface IProductService {

    ResponseProductDTO createProduct(RequestProductDTO requestProductDTO);
    Iterable<ResponseProductDTO> findAllProduct();
    ResponseProductDTO findProductById(Long id);
    ResponseProductDTO updateProduct(Long id,RequestProductUpdateDTO requestProductUpdateDTO);
    void deleteProductById(Long id);

}
