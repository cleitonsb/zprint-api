package br.zprint.controller;

import br.zprint.model.Usuario;
import br.zprint.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity init(@PathVariable(value = "id") Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<Usuario>> list() {
        List<Usuario> list = (List<Usuario>) repository.findAll();
        return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Usuario> store(@RequestBody Usuario usuario) {
        Usuario usuarioStored = repository.save(usuario);
        return new ResponseEntity<Usuario>(usuarioStored, HttpStatus.OK);
    }

    @PutMapping(value = "", produces = "application/json")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
        Usuario usuarioStored = repository.save(usuario);
        return new ResponseEntity<Usuario>(usuarioStored, HttpStatus.OK);
    }
}
