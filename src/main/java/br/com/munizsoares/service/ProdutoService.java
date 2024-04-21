package br.com.munizsoares.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.munizsoares.dto.ProdutoDto;
import br.com.munizsoares.entity.Produto;
import br.com.munizsoares.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

   // public ProdutoService(ProdutoRepository produtoRepository){
   // this.produtoRepository= produtoRepository;
   // }

   /*
    - Cada produto deve ter um nome, preço e uma categoria 
    (bebida, entrada, prato principal, sobremesa). 

   */

    public Object salvarProduto(ProdutoDto dto){
        
        Produto produto = new Produto();
        
        if(dto.getCategoria() == null || dto.getCategoria().isEmpty()){
        return "CATEGORIA DO PRODUTO NÃO FOI INFORMADA";
        }
        produto.setCategoria(dto.getCategoria());
        if(dto.getNome() == null || dto.getNome().isEmpty()){
        return "NOME DO PRODUTO NÃO FOI INFORMADA"; 
        }
        produto.setNome(dto.getNome());
        if(dto.getPreco() == null ){
        return "PREÇO DO PRODUTO NÃO FOI INFORMADO";
        }
        produto.setPreco(dto.getPreco());
        var result = produtoRepository.save(produto);
        return result ;
    }

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();

    }
    
    public Object buscarPorId(Long produtoId){
       
        if(produtoId != null){
        return produtoRepository.buscarProdutoPorId(produtoId);
        }
        return "ID DO PRODUTO NÃO FOI INFORMADO" ;
    }

    public List<Produto> bucarPorCategoria(String categoria){

        if(categoria != null && !categoria.isEmpty()){
            return produtoRepository.buscarProdutoPorCategoria(categoria);
        }
        return null;

        }

    
        public Object deletarProduto(Long produtoId){

            if(produtoId != null){
               Produto produto = produtoRepository.buscarProdutoPorId(produtoId);
               produtoRepository.delete(produto);
               return "PRODUTO FOI DELETADO COM SUCESSO!" ;
            }
            return "PRODUTO NÃO ENCONTRADO" ;
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
            return "PRODUTO NÃO ENCONTRADO" ;
           
        }
}
