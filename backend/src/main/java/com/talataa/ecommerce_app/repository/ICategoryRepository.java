package com.talataa.ecommerce_app.repository;

import com.talataa.ecommerce_app.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepository extends CrudRepository<Category,Long> {
}
