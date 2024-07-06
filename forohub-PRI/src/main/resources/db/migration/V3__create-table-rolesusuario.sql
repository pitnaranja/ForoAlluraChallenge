
create table  usuarioroles (
    PRIMARY KEY (usuario_id, roles_id),
    usuario_id bigint not null ,
    roles_id bigint not null ,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (roles_id) REFERENCES roles(id)
);
