package com.aluracursos.apirestchallengeforo.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_creacion;
    private boolean status;
    private Long autor_id;
    private Long curso_id;

    public Topico(DTOTopico topicoDTO) {
        this.titulo = topicoDTO.titulo();
        this.mensaje = topicoDTO.mensaje();
        this.fecha_creacion = topicoDTO.fecha_creacion();
        this.status = true;
        this.autor_id = 1L;
        this.curso_id = 1L;
    }

    public void actualizarDatos(DTOTopico datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.fecha_creacion() != null) {
            this.fecha_creacion = datos.fecha_creacion();
        }
        if (datos.autor_id() != null) {
            this.autor_id = datos.autor_id();
        }
        if (datos.curso_id() != null) {
            this.curso_id = datos.curso_id();
        }
        this.status = datos.status();
    }


    public void desactivarTopico(){this.status=false;}
}
