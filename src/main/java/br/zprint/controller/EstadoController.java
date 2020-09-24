package br.zprint.controller;

import br.zprint.model.Estado;
import br.zprint.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estado")
public class EstadoController {

    @Autowired
    EstadoRepository repository;

    @GetMapping(value = {""}, produces = "application/json")
    public ResponseEntity<List<Estado>> init() {
        List<Estado> list = (List<Estado>) repository.findAll();
        return new ResponseEntity<List<Estado>>(list, HttpStatus.OK);
    }
}
