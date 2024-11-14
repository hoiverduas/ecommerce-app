package com.talataa.ecommerce_app.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private static String SECRET_KEY = "HD_JavaDv";
    private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public String create(String userName){
        return JWT.create()
                .withSubject(userName)
                .withIssuer("JavaDv")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5) ))
                .sign(ALGORITHM);
    }

    public boolean isValid(String jwt){

        try {

            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
                return true;

        }catch (JWTVerificationException e){
            return false;
        }

    }

    public String getUserName(String jwt){
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }
}
