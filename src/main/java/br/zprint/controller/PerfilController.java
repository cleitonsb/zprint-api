package br.zprint.controller;

import br.zprint.model.Perfil;
import br.zprint.model.Usuario;
import br.zprint.repository.PerfilRepository;
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
@RequestMapping(value = "/perfil")
public class PerfilController {

    @Autowired
    PerfilRepository repository;

    @GetMapping(value = {""}, produces = "application/json")
    public ResponseEntity<List<Perfil>> listByAll() {
        List<Perfil> list = (List<Perfil>) repository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
