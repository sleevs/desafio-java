package br.com.munizsoares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.munizsoares.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value ="select * from Cliente c where c.email = ?1" , nativeQuery = true)
    public Cliente buscarPorEmail(String email);
    
}
