package br.com.munizsoares.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.munizsoares.entity.PedidoProduto;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto,Long>{
    
    @Query(value ="select * from PedidoProduto p where p.produtoId =?1" , nativeQuery=true)
    public PedidoProduto buscarProdutoPorProdutoId(Long id);

    @Query(value ="select * from PedidoProduto p where p.pedidoId =?1" , nativeQuery=true)
    public  List<PedidoProduto> buscarPedidoProdutoPorPedidoId(Long id);

}
