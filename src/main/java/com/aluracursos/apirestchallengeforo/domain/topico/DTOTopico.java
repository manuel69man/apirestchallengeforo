package com.aluracursos.apirestchallengeforo.domain.topico;

import java.time.LocalDateTime;

public record DTOTopico(
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        boolean status,
        Long autor_id,
        Long curso_id

) {
}
