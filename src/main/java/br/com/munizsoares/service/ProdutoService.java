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

    public List<Produto> buscarProdutos(){
        return produtoRepository.findAll();

    }
    
    public Object encontarProduto(Long produtoId){
       
        if(produtoId != null){
        return produtoRepository.buscarProdutoPorId(produtoId);
        }
        return "ID DO PRODUTO NÃO FOI INFORMADO" ;
    }

    public Object encontarProdutoPorNome(String produtoNome){

        if(produtoNome != null && !produtoNome.isEmpty()){
            return produtoRepository.buscarProdutoPorNome(produtoNome);
        }
        return "Produto Não encontrado" ;

        }

    
        public Object deletarProduto(Long produtoId){

            if(produtoId != null){
               Produto produto = produtoRepository.buscarProdutoPorId(produtoId);
               produtoRepository.delete(produto);
               return "Produto foio deletado com sucesso!" ;
            }
            return "Produto Não encontrado" ;
        }


        public Object atuailizarProduto(ProdutoDto dto){

            if(dto != null){
                return salvarProduto(dto) ;
            }
            return "Produto Não encontrado" ;
           
        }
}
