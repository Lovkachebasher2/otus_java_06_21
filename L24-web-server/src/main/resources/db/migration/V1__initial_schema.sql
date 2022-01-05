-- Для @GeneratedValue(strategy = GenerationType.IDENTITY)
/*
create table client
(
    id   bigserial not null primary key,
    name varchar(50)
);

 */

-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
create sequence hibernate_sequence start with 3 increment by 1;

create table users
(
    id       bigint not null primary key,
    login    varchar(50),
    role     varchar(50),
    password varchar(100)
);

insert into users (id, login, role, password) values (1, 'admin','ADMIN', 'admin');
insert into users (id, login, role, password) values (2, 'tg0d','USER', '123');


create table clients (
    id bigint not null primary key,
    name varchar(50),
    phone_number varchar(12),
    address varchar(100)
);


insert into clients (id, name, phone_number, address) values (1, 'admin', '1231237', 'г.Саранск ул Николаева');
insert into clients (id, name, phone_number, address) values (2, 'artem', '8987213', 'address#2');