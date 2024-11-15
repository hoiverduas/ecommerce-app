package com.talataa.ecommerce_app.service.impl;

import com.talataa.ecommerce_app.entities.Admin;
import com.talataa.ecommerce_app.repository.IAdminRepository;
import com.talataa.ecommerce_app.service.IAdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AdminService implements IAdminService {

    private final IAdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    public AdminService(IAdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Admin saveDoctor(Admin doctor) {
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        return this.adminRepository.save(doctor);
    }

    @Override
    public List<Admin> findAllDoctor() {
        return this.adminRepository.findAll();
    }

    @Override
    public Optional<Admin> findByDoctorId(Long id) {
        return this.adminRepository.findById(id).or(() ->{
            throw new RuntimeException("Not found");
        });
    }

    @Override
    public Admin update(Admin admin) {

        Optional<Admin> optionalAdmin = findByDoctorId(admin.getId());

        if (optionalAdmin.isPresent()){
            Admin existAdmin = optionalAdmin.get();

            existAdmin.setAccessLevel(admin.getAccessLevel());
            existAdmin.setEmail(admin.getEmail());
            existAdmin.setPassword(admin.getPassword());
            existAdmin.setUserName(admin.getUserName());
            existAdmin.setAreaOfResponsibility(admin.getAreaOfResponsibility());

            return this.adminRepository.save(existAdmin);
        }else {
            throw new RuntimeException("Not found");
        }
    }

    @Override
    public void deleteById(Long id) {

        Optional<Admin> deleteDoctor = findByDoctorId(id);

        if (deleteDoctor.isPresent()){
            this.adminRepository.deleteById(id);
        }else {
            throw new RuntimeException("Not found");
        }
    }
}
