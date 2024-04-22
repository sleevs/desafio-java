package br.com.munizsoares.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.munizsoares.dto.ProdutoDto;
import br.com.munizsoares.entity.Produto;
import br.com.munizsoares.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("produto")
public class ProdutoController {

    
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
    this.produtoService = produtoService;
    }

    @Operation(summary="API responsável pelo registro de produtos no sistema")
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

    @Operation(summary="API responsável pela buscar de produtos registrados no sistema a partir de ID de registro")
    @GetMapping("/buscar")
    public ResponseEntity<ProdutoDto> buscar(@RequestParam("id")  Long produtoId){
        
        try{
         
            return  ResponseEntity.ok(produtoService.buscarProduto(produtoId));
          
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

  
    @Operation(summary="API responsável por listar um catálogo de  produtos registrados no sistemas - Após realizar a vizualização do catálogo fica mais simple realizar busca a partir do ID do produto")
    @GetMapping(value="/listar")
    public ResponseEntity<List<Produto>> listarProdutos() {
        
        return ResponseEntity.ok(produtoService.listarProdutos());
    }

    @Operation(summary="API responsável pela atualização de produto")
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

    @Operation(summary="API responsável pela exclusão de produtos registrados no sistema a partir do ID do produto")
    @DeleteMapping("/deletar")
    public ResponseEntity<Object> deletar(@RequestParam("id") Long produtoId){
        try{
         
            return  ResponseEntity.ok(produtoService.deletarProduto(produtoId));
          
        }catch(Exception e){
            return new ResponseEntity<>(e , HttpStatus.BAD_REQUEST);
        }
    }
}
