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
    id       bigserial not null primary key,
    login    varchar(50),
    role     varchar(50),
    password varchar(50)
);

create table clients
(
    id         bigserial not null primary key,
    name       varchar(50),
    address_id bigint
);

create table phones
(
    id        bigserial not null primary key,
    number    varchar(50),
    client_id bigint
);

create table addresses
(
    id     bigserial not null primary key,
    street varchar(255)
);