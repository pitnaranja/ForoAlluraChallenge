create table censura(


    id bigint not null auto_increment,
    palabra varchar(20) not null,
    repeticiones int not null ,
    activo boolean not null,

    primary key(id)

);