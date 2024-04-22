package br.com.munizsoares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.munizsoares.dto.PedidoDto;
import br.com.munizsoares.entity.Pedido;
import br.com.munizsoares.repository.PedidoRepository;
import br.com.munizsoares.util.Transformar;

@Service
public class PedidoService implements Transformar<Pedido , PedidoDto>{
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoDto salvarPedido(Pedido entity){

        return transformarDto(pedidoRepository.save(entity));
      
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
            return transformarDto(pedido);
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



  
 

    @Override
    public Pedido transformarEntity(PedidoDto dto) {

        Pedido pedido = new Pedido();
        if(dto.getValorTotal() != null){
            pedido.setValorTotal(dto.getValorTotal());
        }
        if(dto != null){
            pedido.setValorTotal(dto.getValorTotal());
        }

        return pedido;
    }

    @Override
    public PedidoDto transformarDto(Pedido entity) {

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
