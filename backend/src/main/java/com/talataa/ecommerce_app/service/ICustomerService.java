package com.talataa.ecommerce_app.service;

import com.talataa.ecommerce_app.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    Customer savePatient(Customer customer);
    List<Customer> findAllPatient();
    Optional<Customer> findByIdPatient(Long id);
    Customer updatePatient(Customer customer);
    void deleteById(Long id);

}
