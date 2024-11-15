package com.talataa.ecommerce_app.controller;


import com.talataa.ecommerce_app.config.JwtUtil;
import com.talataa.ecommerce_app.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUserName(),loginDto.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(login);
        System.out.println("authentication = " + authentication.isAuthenticated());
        System.out.println("authentication = " + authentication.getPrincipal());

        String jwt = this.jwtUtil.create(loginDto.getUserName());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwt).build();

    }
}
