package br.com.munizsoares.dto;

public class ProdutoDto {

    private Long id;
    private String categoria;
    private Float preco;
    private String nome;
    
    public ProdutoDto(){
        
    }

    public Long getId() {
        return id;
    }

   public void setId(Long id) {
       this.id = id;
   }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
