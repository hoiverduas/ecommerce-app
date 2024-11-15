package com.talataa.ecommerce_app.controller;

import com.talataa.ecommerce_app.entities.Customer;
import com.talataa.ecommerce_app.service.impl.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    public ResponseEntity<Customer> savePatient(@RequestBody Customer customer){
        return ResponseEntity.status(HttpStatus.OK).body(this.customerService.savePatient(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllPatient(){
        return ResponseEntity.status(HttpStatus.OK).body(this.customerService.findAllPatient());
    }

    @GetMapping("/patientId/{id}")
    public ResponseEntity<Optional<Customer>> getByPatientId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.customerService.findByIdPatient(id));
    }


    @PutMapping("/update")
    public  ResponseEntity<Customer> updatePatient(@RequestBody Customer customer){
        return ResponseEntity.status(HttpStatus.OK).body(this.customerService.updatePatient(customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>  deleteByPatientId(@PathVariable Long id){
        this.customerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }





}
