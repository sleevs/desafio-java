package br.com.munizsoares.util;

public enum Constante {

    ID_NAO_INFORMADO("O ID não foi informado !"),
    NOME_NAO_INFORMADO("O Nome não foi informado !"),
    EMAIL_NAO_INFORMADO("O Email não foi informado !"),
    SENHA_NAO_INFORMADO("A Senha não foi informada !");

    private String valor;

    Constante(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
