package com.talataa.ecommerce_app.repository;


import com.talataa.ecommerce_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);

}
