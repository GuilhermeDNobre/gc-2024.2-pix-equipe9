package com.ufc.pix.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ufc.pix.exception.BusinessException;
import com.ufc.pix.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${security.config.key}")
    private String secretKey;
    @Value("${security.config.expiration}")
    private int expireTime;
    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("pix-api") //nome do criador
                    .withSubject(user.getEmail()) //usuario q esta recebendo
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new BusinessException("Error while generating token", exception);
        }
    }
    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(expireTime).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("pix-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return "";
        }
    }
}
