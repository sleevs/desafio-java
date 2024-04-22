package br.com.munizsoares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.munizsoares.dto.ClienteDto;
import br.com.munizsoares.entity.Cliente;
import br.com.munizsoares.repository.ClienteRepository;
import br.com.munizsoares.util.Constante;
import br.com.munizsoares.util.Transformar;
import java.util.logging.Logger;

@Service
public class ClienteService implements Transformar<Cliente, ClienteDto>{
    private static final Logger LOGGER = Logger.getLogger(ClienteService.class.getName());
   
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteDto transformarDto(Cliente entity){
        
        ClienteDto clienteDto = new ClienteDto();
        
        if(entity.getId() == null){
            LOGGER.severe(Constante.ID_NAO_INFORMADO.getValor());
        }
        clienteDto.setId(entity.getId());
        
        if(entity.getEmail() == null || entity.getEmail().isEmpty()){
            LOGGER.severe(Constante.EMAIL_NAO_INFORMADO.getValor());
        }
        clienteDto.setEmail(entity.getEmail());

        if(entity.getSenha() == null || entity.getSenha().isEmpty()){
            LOGGER.severe(Constante.SENHA_NAO_INFORMADO.getValor());
        } 
        clienteDto.setSenha(entity.getSenha());
     
        if(entity.getNome() == null || entity.getNome().isEmpty()){
            LOGGER.severe(Constante.NOME_NAO_INFORMADO.getValor());
        } 
        clienteDto.setNome(entity.getNome());
         
        return clienteDto;
    
    }

    @Override
    public Cliente transformarEntity(ClienteDto dto) {

        Cliente cliente = new Cliente();
        
        if(dto.getNome() == null || dto.getNome().isEmpty() ){
            LOGGER.severe(Constante.NOME_NAO_INFORMADO.getValor());
        }
        cliente.setNome(dto.getNome());
        if(dto.getEmail() == null || dto.getEmail().isEmpty()){
            LOGGER.severe(Constante.EMAIL_NAO_INFORMADO.getValor());
        }
        cliente.setEmail(dto.getEmail());

        if(dto.getSenha() != null || !dto.getSenha().isEmpty()){
            LOGGER.severe(Constante.SENHA_NAO_INFORMADO.getValor());
        }
        cliente.setSenha(dto.getSenha());
       
        return cliente ;
    }

    public ClienteDto salvarCliente(ClienteDto entity) throws Exception{

        return transformarDto(clienteRepository.save(transformarEntity(entity)));
    }
    
}
