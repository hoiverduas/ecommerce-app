package com.talataa.ecommerce_app.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    @Column(unique = true)
    private String email;




    @Transient
    @JsonProperty("role")
    public String getRole() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

}
