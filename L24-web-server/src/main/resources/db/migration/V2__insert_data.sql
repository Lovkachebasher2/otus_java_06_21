insert into users(id, login, role, password)
values (1, 'admin', 'ADMIN', 'admin');

insert into addresses(id, street)
VALUES (1, 'street One');
insert into addresses(id, street)
VALUES (2, 'street Two');

insert into clients (id, name, address_id)
values (1, 'client#1', 1);
insert into clients (id, name, address_id)
values (2, 'client#2', 2);

insert into phones(id, number, client_id)
values (1, '823u1231', 1);
insert into phones(id, number, client_id)
values (2, '823u1w1', 2);