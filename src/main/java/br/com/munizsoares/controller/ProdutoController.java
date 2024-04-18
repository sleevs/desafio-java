package br.com.munizsoares.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.service.annotation.DeleteExchange;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
@RequestMapping("produto")
public class ProdutoController {
    


    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody Object produto){
        
        return null ;
    }

    @GetMapping
    public ResponseEntity<Object> buscar(@RequestBody Object produto){
        
        return null ;
    }

    @GetMapping
    public ResponseEntity<Object> listar(@RequestBody Object produto){
        
        return null ;
    }

    @PutMapping
    public ResponseEntity<Object> atualizar(@RequestBody Object produto){
        
        return null ;
    }

    @DeleteMapping
    public ResponseEntity<Object> deletar(@RequestBody Object produto){
        
        return null ;
    }
}
