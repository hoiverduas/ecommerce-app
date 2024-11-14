package com.talataa.ecommerce_app.service;

import com.talataa.ecommerce_app.dto.loginDto.RequestRegisterDto;
import com.talataa.ecommerce_app.dto.loginDto.ResponseRegisterDto;
import com.talataa.ecommerce_app.model.User;

import java.util.Optional;


public interface IUserService {

    User createUser(User user);
    Iterable<User> findAllUser();
    User findByEmail(String email);
    User findUserById(Long id);
    User updateUser(User user);
    ResponseRegisterDto register(RequestRegisterDto requestRegisterDto);
    void deleteUserById(Long id);

}
