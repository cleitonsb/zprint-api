package br.zprint.controller;

import br.zprint.model.Conta;
import br.zprint.model.Venda;
import br.zprint.repository.ContaRepository;
import br.zprint.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/conta")
public class ContaController {

    @Autowired
    ContaRepository repository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity init(@PathVariable(value = "id") Long id) {
        Optional<Conta> Conta = repository.findById(id);
        return new ResponseEntity<Conta>(Conta.get(), HttpStatus.OK);
    }

    @GetMapping(value = {"/page/{page}", "/page/{page}/busca/{param}"}, produces = "application/json")
    public ResponseEntity<Page<Conta>> list(@PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "param", required = false) String param) {

        PageRequest pageRequest = PageRequest.of(page-1, 5, Sort.by("id"));

        Page<Conta> list = null;

        if(param != null) {
            list = repository.findByParam(param, pageRequest);
        }else{
            list = repository.findAll(pageRequest);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/busca/{param}"}, produces = "application/json")
    public ResponseEntity<List<Conta>> listByAll(@PathVariable(value = "param", required = false) String param) {
        List<Conta> list;

        if (param.equals("noCaixa")) {
            list = (List<Conta>) repository.findOpen();
        }else{
            list = (List<Conta>) repository.findByParam(param);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Conta> store(@RequestBody Conta Conta) {
        Conta ContaStorade = repository.save(Conta);
        return new ResponseEntity<>(ContaStorade, HttpStatus.OK);
    }

    @PutMapping(value = "", produces = "application/json")
    public ResponseEntity<Conta> update(@RequestBody Conta Conta) {
        Conta ContaStored = repository.save(Conta);
        return new ResponseEntity<Conta>(ContaStored, HttpStatus.OK);
    }


}
