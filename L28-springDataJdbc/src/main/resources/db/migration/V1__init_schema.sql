create table client
(
    id   bigserial not null primary key,
    login varchar(50),
    role varchar(50)
);