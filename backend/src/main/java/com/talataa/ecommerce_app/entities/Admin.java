package com.talataa.ecommerce_app.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
public class Admin extends User{

    private Integer accessLevel;
    @Enumerated(EnumType.STRING)
    private AreaOfResponsibility areaOfResponsibility;

}
