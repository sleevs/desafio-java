package br.com.munizsoares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.munizsoares.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {


    @Query(value ="select * from produto p where p.nome =?1" , nativeQuery=true)
    public Produto buscarProdutoPorNome(String nome);


    @Query(value ="select * from produto p where p.id =?1" , nativeQuery=true)
    public Produto buscarProdutoPorId(Long id);
    
}
