package br.com.munizsoares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.munizsoares.dto.PedidoDto;
import br.com.munizsoares.entity.Pedido;
import br.com.munizsoares.repository.PedidoRepository;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoDto salvarPedido(Pedido entity){

        return transformar(pedidoRepository.save(entity));
      
    }
    /*
     
    - Cada pedido deve conter um ou mais produtos e o valor total do pedido.
    - Implementar a funcionalidade de adicionar produtos a um pedido.
     * 
    */

    /*
    
      Criar e listar pedidos.
    */
 


   


       public PedidoDto buscarPedido(Long id){

        if(id != null){
            Pedido pedido = pedidoRepository.buscarPedidoPorId(id);
            return transformar(pedido);
        }
        return null;    

       }





       public Pedido atuailizarPedido(PedidoDto dto){
            
            Pedido pedidoUpdate = pedidoRepository.buscarPedidoPorId(dto.getId());
            if(dto.getId() != null ){
                pedidoUpdate.setId(dto.getId());
            }
            if(dto.getValorTotal() != null){
                pedidoUpdate.setValorTotal(dto.getValorTotal());
            }
                         
            return pedidoRepository.save(pedidoUpdate) ;
        
         }



    public Pedido transformar(PedidoDto dto){

        Pedido pedido = new Pedido();
        if(dto.getValorTotal() != null){
            pedido.setValorTotal(dto.getValorTotal());
        }

        return pedido;
    }

    public PedidoDto transformar(Pedido entity){

        PedidoDto dto = new PedidoDto();
        if(entity.getId() != null){
            dto.setId(entity.getId());
        }

        if(entity.getValorTotal() != null){
            dto.setValorTotal(entity.getValorTotal());
        }

        return dto;
    }
    
}
