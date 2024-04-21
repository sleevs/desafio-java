package br.com.munizsoares.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.munizsoares.entity.Pedido;
import br.com.munizsoares.entity.PedidoProduto;
import br.com.munizsoares.entity.Produto;
import br.com.munizsoares.repository.PedidoProdutoRepository;
import br.com.munizsoares.repository.PedidoRepository;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    /*
     
    - Cada pedido deve conter um ou mais produtos e o valor total do pedido.
    - Implementar a funcionalidade de adicionar produtos a um pedido.
     * 
    */

    /*
    
      Criar e listar pedidos.
    */
    public Object criarPedido(List<Produto> produtos ){
    
        Pedido pedido = new Pedido();
        var pedidoAtual = pedidoRepository.save(pedido);
        List<PedidoProduto> carrinhoProdutos = new ArrayList<PedidoProduto>();
    
        if(pedidoAtual != null){
      
        for(Produto produto : produtos){
            
            PedidoProduto pedidoProduto = new PedidoProduto();

            pedidoProduto.setPedidoId(pedidoAtual.getId());
            pedidoProduto.setProdutoId(produto.getId());
            pedidoProduto.setPreco(produto.getPreco());
            
            Float valorTotal = pedidoAtual.getValorTotal() + produto.getPreco();
            pedidoAtual.setValorTotal(valorTotal);
            pedidoRepository.save(pedidoAtual);

            carrinhoProdutos.add(pedidoProdutoRepository.save(pedidoProduto));
        }
             return carrinhoProdutos;
       
       }

        return null;
    }


     /*
     Implementar a funcionalidade de adicionar produtos a um pedido. 
    */
    public Object addProduto(List<Produto> produtos , Long pedidoId){
    
        Pedido pedido = pedidoRepository.buscarPedidoPorId(pedidoId);
        List<PedidoProduto> carrinhoProdutos = new ArrayList<PedidoProduto>();
    
        if(pedido != null){
      
        for(Produto produto : produtos){
            
            PedidoProduto pedidoProduto = new PedidoProduto();

            pedidoProduto.setPedidoId(pedido.getId());
            pedidoProduto.setProdutoId(produto.getId());
            pedidoProduto.setPreco(produto.getPreco());

            Float valorTotal = pedido.getValorTotal() + produto.getPreco();
            pedido.setValorTotal(valorTotal);
            pedidoRepository.save(pedido);

            carrinhoProdutos.add(pedidoProdutoRepository.save(pedidoProduto));
        }
             return carrinhoProdutos;
       
       }

        return null;
    }
    
}
