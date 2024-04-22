package br.com.munizsoares.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.munizsoares.dto.ClienteDto;
import br.com.munizsoares.service.ClienteService;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


     @PostMapping("/novo")
    public ResponseEntity<Object> novoCliente(
        @RequestParam("email") String  email,
        @RequestParam("nome") String  nome,
        @RequestParam("senha") String  senha){
            ClienteDto cliente = new ClienteDto();
            cliente.setSenha(senha);
            cliente.setNome(nome);
            cliente.setEmail(email);
        
        try{
         
            return  ResponseEntity.ok(clienteService.salvarCliente(cliente));
          
        }catch(Exception e){
            return new ResponseEntity<>(e , HttpStatus.BAD_REQUEST);
        }
    }
    
}
