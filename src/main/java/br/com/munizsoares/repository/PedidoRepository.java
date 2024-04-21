package br.com.munizsoares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.munizsoares.entity.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {


     @Query(value ="select * from Pedido p where p.Id =?1" , nativeQuery=true)
    public Pedido buscarPedidoPorId(Long id);

    
}
