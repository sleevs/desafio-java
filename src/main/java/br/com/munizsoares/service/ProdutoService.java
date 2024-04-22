package br.com.munizsoares.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.munizsoares.dto.ProdutoDto;
import br.com.munizsoares.entity.Produto;
import br.com.munizsoares.repository.ProdutoRepository;
import br.com.munizsoares.util.Constante;
import br.com.munizsoares.util.Transformar;

@Service
public class ProdutoService implements Transformar<Produto , ProdutoDto> {

     private static final Logger LOGGER = Logger.getLogger(ProdutoService.class.getName());
   

    @Autowired
    private ProdutoRepository produtoRepository;



    public Object salvarProduto(ProdutoDto dto){
        
        Produto produto = new Produto();
        
        if(dto.getCategoria() == null || dto.getCategoria().isEmpty()){
            return Constante.CATEGORIA_PRODUTO_NAO_INFORMADA.getValor(); 
        }
        produto.setCategoria(dto.getCategoria());
        
        if(dto.getNome() == null || dto.getNome().isEmpty()){
        return Constante.NOME_PRODUTO_NAO_INFORMADO.getValor(); 
        }
        produto.setNome(dto.getNome());
        
        if(dto.getPreco() == null ){
        return Constante.PRECO_PRODUTO_NAO_INFORMADO.getValor(); 
        }
        produto.setPreco(dto.getPreco());
        
        var result = produtoRepository.save(produto);
        LOGGER.info(Constante.PRODUTO_REGISTRADO.getValor());
        return result ;
    }

        public List<Produto> listarProdutos(){
            return produtoRepository.findAll();
        }

    
    public ProdutoDto buscarProduto(Long produtoId){
       
        if(produtoId != null){
        Produto produto = produtoRepository.buscarProdutoPorId(produtoId);
        return transformarDto(produto);
        }
        LOGGER.info(Constante.PRODUTO_NAO_ENCONTRADO.getValor());
        return  null ;
    }

    public List<Produto> bucarPorCategoria(String categoria){

        if(categoria != null && !categoria.isEmpty()){
            return produtoRepository.buscarProdutoPorCategoria(categoria);
        }
        LOGGER.info(Constante.CATEGORIA_NAO_ENCONTRADO.getValor());
        return null;

        }

    
        public Object deletarProduto(Long produtoId){

            if(produtoId != null){
               Produto produto = produtoRepository.buscarProdutoPorId(produtoId);
               produtoRepository.delete(produto);
               return Constante.PRODUTO_DELETADO.getValor() ;
            }
            return Constante.PRODUTO_NAO_ENCONTRADO.getValor() ;
        }


        public Object atuailizarProduto(ProdutoDto dto){

            if(dto.getId() != null){
                
                Produto produtoUpdate = produtoRepository.buscarProdutoPorId(dto.getId());
                if(dto.getCategoria() != null || !dto.getCategoria().isEmpty()){
                    produtoUpdate.setCategoria(dto.getCategoria());
                }
                if(dto.getNome() != null || !dto.getNome().isEmpty()){
                produtoUpdate.setNome(dto.getNome());
                }
                if(dto.getPreco() != null ){
                produtoUpdate.setPreco(dto.getPreco());  
                }              
                return produtoRepository.save(produtoUpdate) ;
            }
            return Constante.PRODUTO_NAO_ENCONTRADO.getValor() ;
           
        }





     

        @Override
        public Produto transformarEntity(ProdutoDto dto) {
            
            Produto produto = new Produto();
            
            if(dto.getCategoria() != null || !dto.getCategoria().isEmpty()){
            produto.setCategoria(dto.getCategoria());
            }
        
            if(dto.getNome() != null || !dto.getNome().isEmpty()){
            produto.setNome(dto.getNome());
            }
        
            if(dto.getPreco() != null ){
                produto.setPreco(dto.getPreco());
            }
                return produto;
        
        }

        @Override
        public ProdutoDto transformarDto(Produto entity) {
            ProdutoDto produtoDto = new ProdutoDto();

            if(entity.getId() != null ){
                produtoDto.setId(entity.getId());
                }

            if(entity.getCategoria() != null || !entity.getCategoria().isEmpty()){
            produtoDto.setCategoria(entity.getCategoria());
            }
        
            if(entity.getNome() != null || !entity.getNome().isEmpty()){
                produtoDto.setNome(entity.getNome());
            }
        
            if(entity.getPreco() != null ){
                produtoDto.setPreco(entity.getPreco());
            }
                return produtoDto;
        }
}
