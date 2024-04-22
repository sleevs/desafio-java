package br.com.munizsoares.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.munizsoares.entity.Cliente;
import br.com.munizsoares.repository.ClienteRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    SecurityToken securityToken;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        String token = recoveryToken(request);

        if(token != null){
                String email = securityToken.validarToken(token); 
                Cliente cliente = clienteRepository.buscarPorEmail(email);

                var authentication = new UsernamePasswordAuthenticationToken(cliente, null, cliente.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        
    }   
    

    private String recoveryToken (HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null ){
            return null;
        }
        
        return authHeader.replace("Bearer ", "");
        
    }


}
