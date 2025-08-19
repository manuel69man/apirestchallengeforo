package com.aluracursos.apirestchallengeforo.controller;

import com.aluracursos.apirestchallengeforo.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
//@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DTOTopico> registrarMedico(@RequestBody @Valid DTOTopico datosRegistroTopico,
                                                     UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico datosRespuestaMedico = new DatosRespuestaTopico(topico.getTitulo(), topico.getMensaje(),topico.getFecha_creacion());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRegistroTopico);

    }


    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoMedicos(@PageableDefault(size = 3) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopico::new));

    }


}
