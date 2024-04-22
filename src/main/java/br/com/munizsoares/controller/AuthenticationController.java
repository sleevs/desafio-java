package br.com.munizsoares.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.munizsoares.entity.Cliente;
import br.com.munizsoares.repository.ClienteRepository;
import br.com.munizsoares.security.SecurityToken;
import br.com.munizsoares.util.PoliticaSeguranca;

@Controller
@RequestMapping("security")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private SecurityToken securityToken;

    @PostMapping("/login")
    public ResponseEntity<Object> logar(
        @RequestParam("email") String  email,
        @RequestParam("senha") String  senha){
          
        
        try{
            UsernamePasswordAuthenticationToken userPassword = new UsernamePasswordAuthenticationToken(email, senha);
            Authentication authenticationUsuario = authenticationManager.authenticate(userPassword);
            String token = securityToken.generateToken((Cliente ) authenticationUsuario.getPrincipal());
           
            return  ResponseEntity.ok(token);
          
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação: " + e.getMessage());
        }
    }


    @PostMapping("/novo")
    public ResponseEntity<Object> novoUsuario(
        @RequestParam("email") String  email,
        @RequestParam("regra") PoliticaSeguranca regra,
        @RequestParam("nome") String nome,
        @RequestParam("senha") String  senha){
        
        try{

            if(clienteRepository.buscarPorEmail(email) != null){
                return ResponseEntity.badRequest().build();
            }
             var encryptPassword = new BCryptPasswordEncoder().encode(senha);
             Cliente cliente = new Cliente(nome , email ,senha ,regra);
             cliente.setEmail(email);
             cliente.setSenha(encryptPassword);
             cliente.setNome(nome);
             

             clienteRepository.save(cliente);
             return  ResponseEntity.ok().build();
          
        }catch(Exception e){
            return new ResponseEntity<>(e , HttpStatus.BAD_REQUEST);
        }
    }
    
}
