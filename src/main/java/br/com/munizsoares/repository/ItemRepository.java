package br.com.munizsoares.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.munizsoares.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
    
    @Query(value ="select * from item p where p.pedido_id =?1" , nativeQuery=true)
    public Item buscarProdutoPorProdutoId(Long id);

    @Query(value ="select * from item p where p.pedido_id =?1" , nativeQuery=true)
    public  List<Item> buscarPedidoProdutoPorPedidoId(Long id);


    @Query(value ="SELECT SUM(pp.subtotal) FROM item pp WHERE pp.pedido_id =?", nativeQuery=true)
    public Float somatorioPrecoPorPedidoId(Long pedidoId);

}
