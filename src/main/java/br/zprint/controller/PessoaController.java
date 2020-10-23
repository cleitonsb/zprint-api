package br.zprint.controller;

import br.zprint.model.Pessoa;
import br.zprint.repository.PessoaRepository;
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
@RequestMapping(value = "/pessoa")
public class PessoaController {

    @Autowired
    PessoaRepository repository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity init(@PathVariable(value = "id") Long id) {
        Optional<Pessoa> Pessoa = repository.findById(id);
        return new ResponseEntity<Pessoa>(Pessoa.get(), HttpStatus.OK);
    }

    @GetMapping(value = {"/page/{page}", "/page/{page}/busca/{param}"}, produces = "application/json")
    public ResponseEntity<Page<Pessoa>> list(@PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "param", required = false) String param) {

        PageRequest pageRequest = PageRequest.of(page-1, 5, Sort.by("nome"));

        Page<Pessoa> list = null;

        if(param != null) {
            list = repository.findByParam(param, pageRequest);
        }else{
            list = repository.findAll(pageRequest);
        }

        return new ResponseEntity<Page<Pessoa>>(list, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/busca/{param}"}, produces = "application/json")
    public ResponseEntity<List<Pessoa>> listByAll(@PathVariable(value = "param", required = false) String param) {
        param = (param != null) ? param.toUpperCase() : "";

        List<Pessoa> list = (List<Pessoa>) repository.findByParam(param);
        return new ResponseEntity<List<Pessoa>>(list, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Pessoa> store(@RequestBody Pessoa Pessoa) {
        Pessoa PessoaStored = repository.save(Pessoa);
        return new ResponseEntity<Pessoa>(PessoaStored, HttpStatus.OK);
    }

    @PutMapping(value = "", produces = "application/json")
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa Pessoa) {
        Pessoa PessoaStored = repository.save(Pessoa);
        return new ResponseEntity<Pessoa>(PessoaStored, HttpStatus.OK);
    }

    @GetMapping(value = "/remove/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        try{
            repository.softDelete(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

}
