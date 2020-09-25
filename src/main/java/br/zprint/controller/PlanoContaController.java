package br.zprint.controller;

import br.zprint.model.PlanoConta;
import br.zprint.repository.PlanoContaRepository;
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
@RequestMapping(value = "/planoconta")
public class PlanoContaController {

    @Autowired
    PlanoContaRepository repository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity init(@PathVariable(value = "id") Long id) {
        Optional<PlanoConta> PlanoConta = repository.findById(id);
        return new ResponseEntity<PlanoConta>(PlanoConta.get(), HttpStatus.OK);
    }

    @GetMapping(value = {"/page/{page}", "/page/{page}/busca/{param}"}, produces = "application/json")
    public ResponseEntity<Page<PlanoConta>> list(@PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "param", required = false) String param) {

        PageRequest pageRequest = PageRequest.of(page-1, 5, Sort.by("id"));

        Page<PlanoConta> list = null;

        if(param != null) {
            list = repository.findByParam(param, pageRequest);
        }else{
            list = repository.findAll(pageRequest);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/busca/{param}"}, produces = "application/json")
    public ResponseEntity<List<PlanoConta>> listByAll(@PathVariable(value = "param", required = false) String param) {
        List<PlanoConta> list = null;

        if(param != null) {
            list = (List<PlanoConta>) repository.findByParam(param);
        }else{
            list = (List<PlanoConta>) repository.findAll();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<PlanoConta> store(@RequestBody PlanoConta PlanoConta) {
        PlanoConta PlanoContaStorade = repository.save(PlanoConta);
        return new ResponseEntity<>(PlanoContaStorade, HttpStatus.OK);
    }

    @PutMapping(value = "", produces = "application/json")
    public ResponseEntity<PlanoConta> update(@RequestBody PlanoConta PlanoConta) {
        PlanoConta PlanoContaStored = repository.save(PlanoConta);
        return new ResponseEntity<PlanoConta>(PlanoContaStored, HttpStatus.OK);
    }


}
