package br.com.brasilprev.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.brasilprev.api.entity.Tarefa;
import br.com.brasilprev.api.entity.projection.TarefaProjection;
import br.com.brasilprev.api.service.TarefaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefa")
@Validated
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> findById(@PathVariable Long id) {
        Tarefa obj = this.tarefaService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<TarefaProjection>> findAllByUser() {
        List<TarefaProjection> objs = this.tarefaService.findAllByUser();
        return ResponseEntity.ok().body(objs);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Tarefa obj) {
        this.tarefaService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Tarefa obj, @PathVariable Long id) {
        obj.setId(id);
        this.tarefaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
