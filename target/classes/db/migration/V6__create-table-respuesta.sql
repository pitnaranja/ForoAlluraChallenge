create table respuestas(


    id bigint not null auto_increment,
    mensaje varchar(500) not null,
    fechacreacion datetime	 not null ,
    solucion tinyint  not null,
     activo tinyint not null,
    id_topico bigint,
        FOREIGN KEY (id_topico) REFERENCES topicos(id),
    id_usuario bigint,
        FOREIGN KEY (id_usuario) REFERENCES usuarios(id),

    primary key(id)

);
