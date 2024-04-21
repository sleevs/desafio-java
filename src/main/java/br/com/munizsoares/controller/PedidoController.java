package br.com.munizsoares.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.munizsoares.dto.ItemDto;
import br.com.munizsoares.entity.Produto;
import br.com.munizsoares.service.ItemService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


@Controller
@RequestMapping("pedido")
public class PedidoController {
    
    @Autowired
    private ItemService pedidoProdutoService;
    /*
      Cada pedido deve conter um ou mais produtos e o valor total do pedido.
    */
    @PostMapping("/pedido")
    public ResponseEntity<Object> novoPedido(
        @RequestParam ("produtos") List<Long> ids
    
        ){
        
        return null ;
    }


    /*adicionar produto a um pedido*/ 
    @PostMapping("/add_produto")
    public ResponseEntity<List<ItemDto>> adicionarProduto(
        @RequestParam ("produtos") List<Long> ids){
        
     try{
         
            return  ResponseEntity.ok(pedidoProdutoService.addProduto(ids));
          
        }catch(Exception e){
            return new ResponseEntity<List<ItemDto>>((List<ItemDto>) e , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buscar_pedidos")
    public ResponseEntity<Object> buscarPedidos(@RequestBody Object produto){
        
        return null ;
    }
}
