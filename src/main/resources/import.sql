INSERT INTO clientes(id, nombre,apellido,email,create_at,foto) VALUES(1,'Oscar','Cervantes','ocervantes@gmail.com','2023-05-01','');
INSERT INTO clientes(id, nombre,apellido,email,create_at,foto) VALUES(2,'Jesus','Cervantes','JC@gmail.com','2022-03-23','');
INSERT INTO clientes(id, nombre,apellido,email,create_at,foto) VALUES(3,'guadalupe','perez','gp@gmail.com','2023-05-01','');
INSERT INTO clientes(id, nombre,apellido,email,create_at,foto) VALUES(4,'maria','torres','mt@gmail.com','2023-05-01','');
INSERT INTO clientes(id, nombre,apellido,email,create_at,foto) VALUES(5,'diana','liz','dl@gmail.com','2023-05-01','');
INSERT INTO clientes(id, nombre,apellido,email,create_at,foto) VALUES(6,'carolina','llano','cl@gmail.com','2023-05-01','');
INSERT INTO clientes(id, nombre,apellido,email,create_at,foto) VALUES(7,'iris','torrez','it@gmail.com','2023-05-01','');
INSERT INTO clientes(id, nombre,apellido,email,create_at,foto) VALUES(8,'carlos','barona','cb@gmail.com','2023-05-01','');
INSERT INTO clientes(id, nombre,apellido,email,create_at,foto) VALUES(9,'pepe','garcia','pg@gmail.com','2023-05-01','');
INSERT INTO clientes(id, nombre,apellido,email,create_at,foto) VALUES(10,'jose','garcia','jg@gmail.com','2023-05-01','');
INSERT INTO clientes(id, nombre,apellido,email,create_at,foto) VALUES(11,'juan','torrez','jt@gmail.com','2023-05-01','');
INSERT INTO clientes(id, nombre,apellido,email,create_at,foto) VALUES(12,'carlos','liz','cl@gmail.com','2023-05-01','');
INSERT INTO clientes(id, nombre,apellido,email,create_at,foto) VALUES(13,'brian','has','bh@gmail.com','2023-05-01','');

INSERT INTO productos(nombre,precio,create_at) VALUES('Panasonic Pantalla LCD',9990,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('Sony Camara digital',3125,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('Apple iPod shuffle',2990,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('Sonic Notebook Z110',3490,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('Hewlett pack',56990,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('Mica Comoda 5 cajones',59990,NOW());

INSERT INTO facturas(descripcion,observacion, cliente_id, create_at) VALUES('Facturas de oficinas',null,1,NOW());
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES(1,1,1);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES(2,1,4);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES(1,1,5);

Insert into usuarios(usuario,passoword,enabled)values('oscar','$2a$10$xAewWU/RhSl/bDb8Egf3F.gfZq6T7rmlwcg3VV7ThCF2GnHwTu6a2',1);
Insert into usuarios(usuario,passoword,enabled)values('admin','$2a$10$BKGjzg.CmzpWb8HhagprtO06iXumMu0a6bHlQiiu5zCaNXejhVviK',1);

insert into authorities(user_id,authority)values(1,'ROLE_USER');
insert into authorities(user_id,authority)values(2,'ROLE_USER');
insert into authorities(user_id,authority)values(2,'ROLE_ADMIN');

