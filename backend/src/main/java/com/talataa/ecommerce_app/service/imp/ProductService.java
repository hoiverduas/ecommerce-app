package com.talataa.ecommerce_app.service.imp;

import com.talataa.ecommerce_app.model.Product;
import com.talataa.ecommerce_app.repository.IProductRepository;
import com.talataa.ecommerce_app.service.IProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product createProduct(Product product) {
        return this.productRepository
                .save(product);
    }

    @Override
    public Iterable<Product> findAllProduct() {
        return this.productRepository
                .findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return this.productRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public Product updateProduct(Product product) {

        Optional<Product>  productOptional = productRepository.findById(product.getId());

        if (productOptional.isPresent()){
            Product productExist = productOptional.get();

            productExist.setCode(product.getCode());
            productExist.setDateUpdated(LocalDateTime.now());
            productExist.setName(product.getName());
            productExist.setDescription(product.getDescription());
            productExist.setPrice(product.getPrice());
            productExist.setUrlImage(product.getUrlImage());

            return this.productRepository.save(productExist);
        }else {
            throw new RuntimeException("Not found");
        }
    }

    @Override
    public void deleteProductById(Long id) {
       findProductById(id);
       this.productRepository.deleteById(id);
    }
}
