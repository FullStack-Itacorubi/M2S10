INSERT INTO USERS (USERNAME, PASSWORD) VALUES ('gilmar', '$2a$10$U8INEllGbS5SZSupYv5BWOt4xTZuhL0ykqxJXxZ2n2Cn/U5avzIem')
INSERT INTO USERS (USERNAME, PASSWORD) VALUES ('gilmar2', '123')

INSERT INTO ROLES (NAME) VALUES ('PERMISSAO_01')
INSERT INTO ROLES (NAME) VALUES ('PERMISSAO_02')
INSERT INTO ROLES (NAME) VALUES ('PERMISSAO_03')

insert into users_roles (roles_id, users_id) values (1, 1)
insert into users_roles (roles_id, users_id) values (2, 1)
insert into users_roles (roles_id, users_id) values (3, 1)
insert into users_roles (roles_id, users_id) values (2, 2)

insert into authority (name) values ('READ');
insert into authority (name) values ('CREATE');

insert into roles_authorities (authorities_id, roles_entity_id) values (1, 1)
insert into roles_authorities (authorities_id, roles_entity_id) values (2, 1)
insert into roles_authorities (authorities_id, roles_entity_id) values (2, 2)