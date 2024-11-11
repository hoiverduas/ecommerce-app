package com.talataa.ecommerce_app.service.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talataa.ecommerce_app.dto.productDto.RequestProductDTO;
import com.talataa.ecommerce_app.dto.productDto.RequestProductUpdateDTO;
import com.talataa.ecommerce_app.dto.productDto.ResponseProductDTO;
import com.talataa.ecommerce_app.model.Category;
import com.talataa.ecommerce_app.model.Product;
import com.talataa.ecommerce_app.model.User;
import com.talataa.ecommerce_app.repository.ICategoryRepository;
import com.talataa.ecommerce_app.repository.IProductRepository;
import com.talataa.ecommerce_app.repository.IUserRepository;
import com.talataa.ecommerce_app.service.IProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ObjectMapper objectMapper;
    private final IUserRepository userRepository;
    private final ICategoryRepository categoryRepository;

    public ProductService(IProductRepository productRepository, ObjectMapper objectMapper, IUserRepository userRepository, ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;

        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public ResponseProductDTO createProduct(RequestProductDTO requestProductDTO) {

        Long userId = requestProductDTO.getUserId();
        User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new RuntimeException("Not Found"));

        Long categoryId = requestProductDTO.getCategoryId();
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(()->new RuntimeException("Not Found"));

        Product product = mapToEntity(requestProductDTO);
        product.setCategory(category);
        product.setUser(user);
        product.setDateCreated(LocalDateTime.now());

        this.productRepository.save(product);

        ResponseProductDTO productDTO = mapTaDto(product);
        productDTO.setCategoryId(categoryId);
        productDTO.setUserId(userId);
        productDTO.setDateCreated(product.getDateCreated());


       return productDTO;

    }

    @Override
    public Iterable<ResponseProductDTO> findAllProduct() {
        List<Product>products = (List<Product>) productRepository.findAll();
        return products.stream()
                .map(this::mapTaDto)
                .collect(Collectors.toList());

    }

    @Override
    public ResponseProductDTO findProductById(Long id) {

        Product product = productRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Not Found"));
        return mapTaDto(product);
    }

    @Override
    public ResponseProductDTO updateProduct(RequestProductUpdateDTO requestProductUpdateDTO) {

        findProductById(requestProductUpdateDTO.getId());
        Product product = productRepository.save(mapToEntity(requestProductUpdateDTO));
        product.setDateUpdated(LocalDateTime.now());
        return mapTaDto(product);

    }

    @Override
    public void deleteProductById(Long id) {
       findProductById(id);
       this.productRepository.deleteById(id);
    }

    private ResponseProductDTO mapTaDto(Product product) {

        ResponseProductDTO dto = this.objectMapper.convertValue(product, ResponseProductDTO.class);

        if (product.getUser() != null) {
            dto.setUserId(product.getUser().getId());
        }
        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
        }

        return dto;
    }


    private Product mapToEntity(RequestProductDTO requestProductDTO){
        return this.objectMapper
                .convertValue(requestProductDTO,Product.class);
    }

    private Product mapToEntity(RequestProductUpdateDTO requestProductUpdateDTO){
        return this.objectMapper
                .convertValue(requestProductUpdateDTO,Product.class);
    }
}
