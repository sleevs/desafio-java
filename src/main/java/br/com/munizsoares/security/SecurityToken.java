package br.com.munizsoares.security;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.munizsoares.entity.Cliente;

@Service
public class SecurityToken {
    
    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(Cliente cliente){
        
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                               .withIssuer("auth-api")
                               .withSubject(cliente.getEmail())
                               .withExpiresAt(generateExpirationDate())
                               .sign(algorithm);     
            return token ;
        }catch(JWTCreationException e){
            throw new RuntimeException("ERRO AO GERAR O TOKEN  ", e);
        }
    }


    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }


    public String validarToken(String token){

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
                return JWT.require(algorithm)
                           .withIssuer("auth-api")
                           .build()
                           .verify(token)
                           .getSubject();

        }catch(JWTVerificationException e){
            return "";
        }
    }
}
