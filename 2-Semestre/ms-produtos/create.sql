create table tb_categoria (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB;
create table tb_produto (id bigint not null auto_increment, caracteristica varchar(255), descricao varchar(255), nome varchar(255), preco decimal(19,2), categoria_id bigint, primary key (id)) engine=InnoDB;
alter table tb_produto add constraint FK1ksfe2oumgjxke3oc5op41lej foreign key (categoria_id) references tb_categoria (id);
