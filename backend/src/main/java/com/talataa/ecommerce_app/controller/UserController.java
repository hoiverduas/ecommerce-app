package com.talataa.ecommerce_app.controller;


import com.talataa.ecommerce_app.entities.User;
import com.talataa.ecommerce_app.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        return ResponseEntity.ok(this.userService.save(user));
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.findAll());
    }
    @GetMapping("/findUser/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.update(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
