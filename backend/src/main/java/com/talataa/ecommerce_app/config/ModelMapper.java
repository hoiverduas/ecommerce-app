package com.talataa.ecommerce_app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapper {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
