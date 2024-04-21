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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.munizsoares.dto.ProdutoDto;
import br.com.munizsoares.entity.Pedido;
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
    public ResponseEntity<Object> novoProduto(
        @RequestParam("categoria") String  categoria,
        @RequestParam("nome") String  nome,
        @RequestParam("preco") Float  preco){
            ProdutoDto produto = new ProdutoDto();
            produto.setCategoria(categoria);
            produto.setNome(nome);
            produto.setPreco(preco);
        
        try{
         
            return  ResponseEntity.ok(produtoService.salvarProduto(produto));
          
        }catch(Exception e){
            return new ResponseEntity<>(e , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Object> buscar(@RequestParam("id")  Long produtoId){
        
        try{
         
            return  ResponseEntity.ok(produtoService.buscarPorId(produtoId));
          
        }catch(Exception e){
            return new ResponseEntity<>(e , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<Produto>> buscarCategoria(@RequestParam("categoria")  String produtoCategoria){
        
        try{
         
            return  ResponseEntity.ok(produtoService.bucarPorCategoria(produtoCategoria));
          
        }catch(Exception e){
            return new ResponseEntity<List<Produto>>((List<Produto>) e , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/listar")
    public ResponseEntity<List<Produto>> listar(){
        
        return ResponseEntity.ok(produtoService.listarProdutos());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Object> atualizar(
    @RequestParam("id") Long  id,
    @RequestParam("categoria") String  categoria,
    @RequestParam("nome") String  nome,
    @RequestParam("preco") Float  preco){
        try{

            ProdutoDto produto = new ProdutoDto();
            produto.setCategoria(categoria);
            produto.setNome(nome);
            produto.setPreco(preco);
            produto.setId(id);
         
            return  ResponseEntity.ok(produtoService.atuailizarProduto(produto));
          
        }catch(Exception e){
            return new ResponseEntity<>(e , HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<Object> deletar(@RequestParam("id") Long produtoId){
        try{
         
            return  ResponseEntity.ok(produtoService.deletarProduto(produtoId));
          
        }catch(Exception e){
            return new ResponseEntity<>(e , HttpStatus.BAD_REQUEST);
        }
    }
}
