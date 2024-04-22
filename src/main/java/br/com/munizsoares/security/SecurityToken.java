package br.com.munizsoares.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import br.com.munizsoares.entity.Cliente;
import br.com.munizsoares.util.Constante;

@Service
public class SecurityToken {
    
     private static final Logger LOGGER = Logger.getLogger(SecurityToken.class.getName());
    
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
            LOGGER.warning(Constante.ERRO_TOKEN.getValor());
            throw new RuntimeException(Constante.ERRO_TOKEN.getValor(), e);
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
            LOGGER.warning(Constante.ERRO_VERIFICACAO_JWT.getValor().concat(e.getMessage()));
            return "";
        }
    }
}
