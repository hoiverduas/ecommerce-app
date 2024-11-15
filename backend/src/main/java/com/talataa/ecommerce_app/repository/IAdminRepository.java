package com.talataa.ecommerce_app.repository;


import com.talataa.ecommerce_app.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepository extends JpaRepository<Admin,Long> {


}
