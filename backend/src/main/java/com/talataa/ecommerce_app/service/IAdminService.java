package com.talataa.ecommerce_app.service;

import com.talataa.ecommerce_app.entities.Admin;

import java.util.List;
import java.util.Optional;

public interface IAdminService {

    Admin saveDoctor(Admin admin);
    List<Admin> findAllDoctor();
    Optional<Admin> findByDoctorId(Long id);
    Admin update(Admin admin);
    void deleteById(Long id);

}
