package br.com.munizsoares.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.munizsoares.util.PoliticaSeguranca;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome ;
    private String email;
    private String senha;
    private PoliticaSeguranca regra;
    
    public Cliente(){}

    
    public Cliente(String nome ,String email, String senha, PoliticaSeguranca regra) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.regra = regra;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
   
        if(this.regra == PoliticaSeguranca.ADMIN){
             return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER")) ;
        }
    
    }

    @Override
    public String getPassword() {
     
        return senha;
    }

    @Override
    public String getUsername() {
  
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
    
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
    
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    
}
