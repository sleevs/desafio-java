package br.com.munizsoares.util;

public interface Transformar<E,D> {

   public E transformarEntity(D dto);
   public D transformarDto(E entity);
    
}
