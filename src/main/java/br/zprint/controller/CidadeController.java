package br.zprint.controller;

import br.zprint.model.Cidade;
import br.zprint.model.Estado;
import br.zprint.repository.CidadeRepository;
import br.zprint.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cidade")
public class CidadeController {

    @Autowired
    CidadeRepository repository;

    @Autowired
    EstadoRepository estadoRepository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity init(@PathVariable(value = "id") Long id) {
        Optional<Cidade> cidade = repository.findById(id);
        return new ResponseEntity<Cidade>(cidade.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/estado/{estado}", produces = "application/json")
    public ResponseEntity cidadeByEstado(@PathVariable(value = "estado") Long estadoid) throws Exception {

        Optional<Estado> estado = estadoRepository.findById(estadoid);

        if(!estado.isPresent()) throw new Exception("Estado não encontrado");

        List<Cidade> list = repository.findByEstado(estado.get());
        return new ResponseEntity<List<Cidade>>(list, HttpStatus.OK);
    }
}
