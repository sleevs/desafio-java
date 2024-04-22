
create table cliente(
 id bigserial primary key,
 email varchar ,
 senha varchar ,
 nome varchar ,
 regra varchar
);

CREATE TABLE produto (
  id bigserial PRIMARY KEY, 
  categoria VARCHAR , 
  nome varchar ,
  preco float 
);

create table pedido(
 id bigserial primary key,
 valor_total float ,
 cliente_id bigserial
 
);

create table item(
 id bigserial primary key,
 produto_id bigserial ,
 pedido_id bigserial ,
 subtotal float 
);
