create table usuarios(

    id bigint not null auto_increment,
    username varchar(100) not null,
    email varchar(200) not null ,
    password varchar(300) not null ,
    fechaAdmision date ,
    activo tinyint not null,

    primary key(id)

);