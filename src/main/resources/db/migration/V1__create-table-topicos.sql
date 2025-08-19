
create table topicos(

    id bigint not null auto_increment,
    titulo varchar(255) not null unique,
    mensaje varchar(255) not null,
    fecha_creacion datetime not null,
    status tinyint not null,
    autor_id bigint not null,
    curso_id bigint not null,

    primary key(id)

);
