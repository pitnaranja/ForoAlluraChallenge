create table topicos(


    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(500) not null ,
    fechacreacion DATE not null ,
    status boolean not null,
    autor varchar(100) not null,
    cursos varchar(100) not null,
    activo tinyint not null,
    id_usuario bigint,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    id_curso bigint,
     FOREIGN KEY (id_curso) REFERENCES cursos(id),

    primary key(id)

);