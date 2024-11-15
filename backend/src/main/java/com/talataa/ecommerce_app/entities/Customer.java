package com.talataa.ecommerce_app.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("CUSTOMER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User{


    private String fullName;
    private String address;
    private String phoneNumber;

}
