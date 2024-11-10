package com.talataa.ecommerce_app.service.imp;

import com.talataa.ecommerce_app.model.Category;
import com.talataa.ecommerce_app.model.Product;
import com.talataa.ecommerce_app.repository.ICategoryRepository;
import com.talataa.ecommerce_app.service.ICategoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        return this.categoryRepository
                .save(category);
    }

    @Override
    public Iterable<Category> findAllCategory() {
        return this.categoryRepository
                .findAll();
    }

    @Override
    public Category findCategoryById(Long id) {
        return this.categoryRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public Category updateCategory(Category category) {

        Optional<Category> categoryOptional = categoryRepository.findById(category.getId());

        if (categoryOptional.isPresent()){
            Category categoryExist = categoryOptional.get();

            categoryExist.setName(category.getName());
            categoryExist.setDateUpdated(LocalDateTime.now());

            return this.categoryRepository.save(categoryExist);
        }else {
            throw new RuntimeException("Not found");
        }


    }

    @Override
    public void deleteCategoryById(Long id) {
        findCategoryById(id);
        this.categoryRepository.deleteById(id);
    }
}
