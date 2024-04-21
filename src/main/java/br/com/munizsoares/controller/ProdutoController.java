package br.com.munizsoares.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.munizsoares.dto.ProdutoDto;
import br.com.munizsoares.entity.Produto;
import br.com.munizsoares.service.ProdutoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
    this.produtoService = produtoService;
    }

    @PostMapping("/novo")
    public ResponseEntity<Object> novoProduto(@RequestBody ProdutoDto produto){
        
        try{
            return  ResponseEntity.ok(produtoService.salvarProduto(produto));
          
        }catch(Exception e){
            return new ResponseEntity<>(e , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Object>> buscar(@RequestBody Object produto){
        
        return null ;
    }

    @GetMapping(value="/listar")
    public ResponseEntity<List<Produto>> listar(){
        
        return ResponseEntity.ok(produtoService.buscarProdutos());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Object> atualizar(@RequestBody ProdutoDto produto){
        
        return null ;
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<Object> deletar(@RequestBody ProdutoDto produto){
        
        return null ;
    }
}
