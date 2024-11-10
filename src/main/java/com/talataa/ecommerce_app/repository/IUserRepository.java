package com.talataa.ecommerce_app.repository;

import com.talataa.ecommerce_app.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
