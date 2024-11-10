package com.talataa.ecommerce_app.service;

import com.talataa.ecommerce_app.model.Category;

public interface ICategoryService {

    Category createCategory(Category category);
    Iterable<Category> findAllCategory();
    Category findCategoryById(Long id);
    Category updateCategory(Category category);
    void deleteCategoryById(Long id);

}
