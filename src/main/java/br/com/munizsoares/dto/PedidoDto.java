package br.com.munizsoares.dto;

public class PedidoDto {

    private Long id;
    private Float valorTotal;
    

    public PedidoDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    
    
}
