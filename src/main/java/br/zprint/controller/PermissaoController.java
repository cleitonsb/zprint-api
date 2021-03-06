package br.zprint.controller;

import br.zprint.model.Usuario;
import br.zprint.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/permissao")
public class PermissaoController {

    @Autowired
    UsuarioRepository repository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity init(@PathVariable(value = "id") Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/{email}/email", produces = "application/json")
    public ResponseEntity userByEmail(@PathVariable(value = "email") String email) {
        Usuario usuario = repository.findByLogin(email);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @GetMapping(value = {"/page/{page}", "/page/{page}/busca/{param}"}, produces = "application/json")
    public ResponseEntity<Page<Usuario>> list(@PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "param", required = false) String param) {

        PageRequest pageRequest = PageRequest.of(page-1, 5, Sort.by("nome"));

        Page<Usuario> list = null;

        if(param != null) {
            list = repository.findByParam(param, pageRequest);
        }else{
            list = repository.findAll(pageRequest);
        }

        return new ResponseEntity<Page<Usuario>>(list, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/busca/{param}"}, produces = "application/json")
    public ResponseEntity<List<Usuario>> listByAll(@PathVariable(value = "param", required = false) String param) {
        List<Usuario> list = (List<Usuario>) repository.findByParam(param);
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
