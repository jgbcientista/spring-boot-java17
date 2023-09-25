package br.com.brasilprev.api.controller;

import java.net.URI;

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

import br.com.brasilprev.api.entity.Usuario;
import br.com.brasilprev.api.entity.dto.UsuarioCreateDTO;
import br.com.brasilprev.api.entity.dto.UsuarioUpdateDTO;
import br.com.brasilprev.api.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Usuario obj = this.usuarioService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UsuarioCreateDTO obj) {
        Usuario usuario = this.usuarioService.fromDTO(obj);
        Usuario newUser = this.usuarioService.create(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody UsuarioUpdateDTO obj, @PathVariable Long id) {
        obj.setId(id);
        Usuario usuario = this.usuarioService.fromDTO(obj);
        this.usuarioService.update(usuario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
