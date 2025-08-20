package com.aluracursos.apirestchallengeforo.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    Page<Topico> findByStatusTrue(Pageable paginacion);
}
