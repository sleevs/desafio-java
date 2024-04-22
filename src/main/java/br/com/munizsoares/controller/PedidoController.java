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
import br.com.munizsoares.dto.PedidoDto;
import br.com.munizsoares.service.ItemService;
import br.com.munizsoares.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;


@Controller
@RequestMapping("pedido")
public class PedidoController {
    
    @Autowired
    private ItemService itemService;

    @Autowired
    private PedidoService pedidoService;
    

    


    @Operation(summary="API responsável pela realização de pedido e a inclusão de produtos no pedido")
    @PostMapping("/add_produto")
    public ResponseEntity<List<ItemDto>> adicionarProduto(
        @RequestParam ("produtos") List<Long> ids){
        
     try{
         return  ResponseEntity.ok(itemService.addProduto(ids));
          
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    
              }
    }

    @Operation(summary="API responsável pela realização de consulta de pedido a partir do ID do pedido")
    @GetMapping("/buscar_pedidos")
    public ResponseEntity<PedidoDto> buscarPedidos(@RequestParam ("id")Long pedidoId){
        try{
            return  ResponseEntity.ok(pedidoService.buscarPedido(pedidoId));
             
           }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    
              }
       }
}
