package com.aluracursos.apirestchallengeforo.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DTOTopico(
        @NotNull @NotBlank String titulo,
        @NotNull @NotBlank String mensaje,
        @NotNull LocalDateTime fecha_creacion,
        boolean status,
        Long autor_id,
        Long curso_id

) {

}
