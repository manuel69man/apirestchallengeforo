
package com.aluracursos.apirestchallengeforo.controller;

import com.aluracursos.apirestchallengeforo.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
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
    public ResponseEntity<Page<DatosListadoTopico>> listadoMedicos(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopico::new));

    }

    @Transactional

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topico =Optional.of(topicoRepository.getReferenceById(id));
        if (topico.isPresent()){
            if (topico.get().getId()!=null){
                topicoRepository.deleteById(topico.get().getId());
                return ResponseEntity.ok().build();
            }
        }
        throw new EntityNotFoundException();
    }



    @GetMapping("/{id}")
    public ResponseEntity detallarTopicoPorId(@PathVariable Long id) {
        Optional<Topico> topico =Optional.of(topicoRepository.getReferenceById(id));
        if (topico.isPresent() && topico.get().getId()!=null){
            Topico tp = topico.get();
            return ResponseEntity.ok(new DTOTopico(tp.getTitulo(),tp.getMensaje(),tp.getFecha_creacion(),tp.isStatus(),tp.getAutor_id(),tp.getCurso_id()));
        }
        throw new EntityNotFoundException();
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosParaActualizarTopico,@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarDatos(datosParaActualizarTopico);
        return ResponseEntity
                .ok(new DatosActualizarTopico(topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFecha_creacion(),
                        topico.isStatus(),
                        topico.getAutor_id(),
                        topico.getCurso_id()));
    }


}
