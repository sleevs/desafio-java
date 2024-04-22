package br.com.munizsoares.util;

public enum PoliticaSeguranca {
    
    ADMIN("admin"),
    USER("user");
   
    private String valor;

    PoliticaSeguranca(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
