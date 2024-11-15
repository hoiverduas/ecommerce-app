package com.talataa.ecommerce_app.controller;

import com.talataa.ecommerce_app.entities.Admin;
import com.talataa.ecommerce_app.service.impl.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping
    public ResponseEntity<Admin> saveDoctor(@RequestBody Admin admin){
        return ResponseEntity.status(HttpStatus.OK).body(this.adminService.saveDoctor(admin));
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllDoctor(){
        return ResponseEntity.status(HttpStatus.OK).body(this.adminService.findAllDoctor());
    }

    @GetMapping("/doctorId/{id}")
    public ResponseEntity<Optional<Admin>> getByPatientId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.adminService.findByDoctorId(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Admin> updateDoctor(@RequestBody Admin admin){
        return ResponseEntity.status(HttpStatus.OK).body(this.adminService.update(admin));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Admin> deleteById(@PathVariable Long id){
        this.adminService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
