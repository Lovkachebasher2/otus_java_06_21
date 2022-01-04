-- Для @GeneratedValue(strategy = GenerationType.IDENTITY)
/*
create table client
(
    id   bigserial not null primary key,
    name varchar(50)
);

 */

-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
create sequence hibernate_sequence start with 4 increment by 1;

create table users
(
    id       bigint not null primary key,
    login    varchar(50),
    role     varchar(50),
    password varchar(100)
);

insert into users (id, login, role, password) values (1, 'admin','ADMIN', 'admin');
insert into users (id, login, role, password) values (2, 'tg0d','USER', '12313');
insert into users (id, login, role, password) values (3, 'vad','USER', '2131');
