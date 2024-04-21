package br.com.munizsoares.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.munizsoares.dto.PedidoDto;
import br.com.munizsoares.dto.ItemDto;
import br.com.munizsoares.dto.ProdutoDto;
import br.com.munizsoares.entity.Pedido;
import br.com.munizsoares.entity.Item;
import br.com.munizsoares.repository.ItemRepository;

@Service
public class ItemService {
    


    @Autowired
    private ItemRepository pedidoProdutoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private PedidoService pedidoService;



    public ItemDto salvarPedidoProduto(Item entity){
      return  transformar(pedidoProdutoRepository.save(entity));
    }


    public ItemDto transformar(Item entity){

        ItemDto dto = new ItemDto();

        if(entity.getId() != null)
        {dto.setId(entity.getId());}

        if(entity.getProdutoId()  != null){
            ProdutoDto produto = produtoService.buscarProduto(entity.getProdutoId());
            dto.setProduto(produto);
        }

        if(entity.getPedidoId() != null){
            PedidoDto pedido = pedidoService.buscarPedido(entity.getPedidoId());
            dto.setPedido(pedido);
        }

        if(entity.getSubtotal() != null){
            dto.setSubTotal(entity.getSubtotal());
        }

        return dto ;
    }




    public Item transformar(ItemDto dto){

        Item entity = new Item();

        if(dto.getId() != null)
        {dto.setId(entity.getId());}

        if(dto.getProduto().getId()  != null){
            entity.setProdutoId(dto.getProduto().getId());
        }

        if(dto.getPedido().getId() != null){
            entity.setPedidoId(dto.getPedido().getId());
        }

        if(dto.getSubTotal() != null){
            entity.setSubtotal(dto.getSubTotal());
        }

        return entity ;
    }


      /*
     Implementar a funcionalidade de adicionar produtos a um pedido. 
    */
    public List<ItemDto> addProduto(List<Long> ids){
    
        Float valorTotal  = 0.0f;
        List<ItemDto>carrinhoItens = new ArrayList<ItemDto>();
    
        Pedido pedido = new Pedido();
        PedidoDto pedidoAtual = pedidoService.salvarPedido(pedido);

        for(Long produtoId :  ids){
            
            ProdutoDto produto = produtoService.buscarProduto(produtoId);
            ItemDto itemDto = new ItemDto();
            itemDto.setProduto(produto);
            itemDto.setSubTotal(produto.getPreco());

            itemDto.setPedido(pedidoAtual);
            carrinhoItens.add(salvarPedidoProduto(transformar(itemDto)));
        }
           
          Float total = pedidoProdutoRepository.somatorioPrecoPorPedidoId(pedidoAtual.getId());
          PedidoDto pedidoAtualizado = pedidoService.buscarPedido(pedidoAtual.getId()); 
          pedidoAtualizado.setValorTotal(total);
          pedidoService.atuailizarPedido(pedidoAtualizado);
          List<Item> result = pedidoProdutoRepository.buscarPedidoProdutoPorPedidoId(pedidoAtualizado.getId());
          List<ItemDto> data = new ArrayList<ItemDto>(); 
          for(Item p : result){
            data.add(transformar(p));
          }
          
          return data;
       
       }




}
