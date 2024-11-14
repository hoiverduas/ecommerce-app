package com.talataa.ecommerce_app.controller;

import com.talataa.ecommerce_app.dto.loginDto.RequestRegisterDto;
import com.talataa.ecommerce_app.dto.loginDto.ResponseRegisterDto;
import com.talataa.ecommerce_app.model.User;
import com.talataa.ecommerce_app.service.imp.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseRegisterDto> createUser(@RequestBody RequestRegisterDto requestRegisterDto){
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.userService.register(requestRegisterDto));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> findAllUser(){
        try {
            return ResponseEntity.
                    status(HttpStatus.OK)
                    .body(this.userService.findAllUser());
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.userService.findUserById(id));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}