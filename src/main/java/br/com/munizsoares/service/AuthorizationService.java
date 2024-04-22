package br.com.munizsoares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.munizsoares.repository.ClienteRepository;

public class AuthorizationService implements UserDetailsService{

    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  
        return clienteRepository.buscarPorEmail(username);
    }
    
}
