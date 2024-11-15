package com.talataa.ecommerce_app.service.impl;


import com.talataa.ecommerce_app.entities.Customer;
import com.talataa.ecommerce_app.repository.ICustomerRepository;
import com.talataa.ecommerce_app.service.ICustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    public CustomerService(ICustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer savePatient(Customer patient) {

        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        return this.customerRepository.save(patient);
    }

    @Override
    public List<Customer> findAllPatient() {
        return this.customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findByIdPatient(Long id) {
        return this.customerRepository.findById(id).or(()->{
            throw new RuntimeException("not found");
        });
    }

    @Override
    public Customer updatePatient(Customer customer) {

        Optional<Customer>optionalPatient = findByIdPatient(customer.getId());

        if (optionalPatient.isPresent()){

            Customer existCustomer = optionalPatient.get();

            existCustomer.setFullName(customer.getFullName());
            existCustomer.setEmail(customer.getEmail());
            existCustomer.setPassword(customer.getPassword());
            existCustomer.setPhoneNumber(customer.getPhoneNumber());
            existCustomer.setUserName(customer.getUserName());
            existCustomer.setAddress(customer.getAddress());


            return this.customerRepository.save(existCustomer);

        }else {

            throw new RuntimeException("not found");

        }

    }

    @Override
    public void deleteById(Long id) {
         Optional<Customer>optionalPatient = this.findByIdPatient(id);
         if (optionalPatient.isPresent()){
             this.customerRepository.deleteById(id);
         }else {
             throw new RuntimeException("Not Found" + id);
         }
    }
}
