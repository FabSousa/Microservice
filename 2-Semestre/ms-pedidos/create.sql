create table tb_item_do_pedido (id  bigserial not null, descricao varchar(255), quantidade int4 not null, pedido_id int8 not null, primary key (id));
create table tb_pedido (id  bigserial not null, data_hora timestamp not null, status varchar(255) not null, primary key (id));
alter table tb_item_do_pedido add constraint FKfll136fdpqohdxfxd067f63l0 foreign key (pedido_id) references tb_pedido;
