package br.com.munizsoares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.munizsoares.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
