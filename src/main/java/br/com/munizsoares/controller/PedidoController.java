package br.com.munizsoares.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@Controller
@RequestMapping("pedido")
public class PedidoController {
    


    @PostMapping("/pedido")
    public ResponseEntity<Object> criarPedido(@RequestBody Object produto){
        
        return null ;
    }


    /*adicionar produto a um pedido*/ 
    @PostMapping("/add_produto")
    public ResponseEntity<Object> adicionarProduto(@RequestBody Object produto){
        
        return null ;
    }

    @GetMapping("/buscar_pedidos")
    public ResponseEntity<Object> buscarPedidos(@RequestBody Object produto){
        
        return null ;
    }
}
