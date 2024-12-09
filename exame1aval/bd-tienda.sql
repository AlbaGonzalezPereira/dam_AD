drop database if exists tiendaAD;
create database tiendaAD;

use tiendaAD;

create table producto (
    id int primary key auto_increment,
	nombre varchar(250) not null, 
    descripcion varchar(250) not null, 
    precio double not null,
    unique (nombre)
);

create table cliente (
	dni varchar(9) primary key,
    nombre varchar(250) not null
);

create table pedido (
	id int primary key auto_increment,
    dniCliente varchar(9),
    fecha timestamp,
    constraint fk_cliente foreign key (dniCliente) references cliente(dni)
);

create table producto_pedido (
	idPedido int,
    idProducto int,
    cantidad int not null,
    primary key (idPedido, idProducto),
    constraint fk_pedido foreign key (idPedido) references pedido(id),
    constraint fk_producto foreign key (idProducto) references producto(id)
);

insert into producto(nombre, descripcion, precio) values ('pantalla', 'pantalla de 28 pulgadas', 50);
insert into producto(nombre, descripcion, precio) values ('lavadora', 'lavadora blanca', 115.99);
insert into producto(nombre, descripcion, precio) values ('bicicleta', 'bicicleta de montaña', 205.60);
insert into producto(nombre, descripcion, precio) values ('silla', 'silla de madera', 20);

insert into cliente values ('11111111A','Tom');
insert into cliente values ('22222222B','Ann');
insert into cliente values ('33333333C','Lisa');

insert into pedido(dniCliente, fecha) values ('11111111A', now());
insert into pedido(dniCliente, fecha) values ('22222222B', now());

insert into producto_pedido values (1, 1, 3);
insert into producto_pedido values (2, 4, 2);
insert into producto_pedido values (2, 3, 1);

