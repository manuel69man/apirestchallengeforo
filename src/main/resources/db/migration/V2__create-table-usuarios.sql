
create table usuarios(
    id bigint not null auto_increment,
    nombre varchar(255) not null,
    correo varchar(255) not null,
    contasena varchar(255) not null unique,

    primary key(id)

);
