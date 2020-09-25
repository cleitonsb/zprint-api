package br.zprint.controller;

import br.zprint.model.Caixa;
import br.zprint.model.Venda;
import br.zprint.repository.CaixaRepository;
import br.zprint.repository.VendaRepository;
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
@RequestMapping(value = "/caixa")
public class CaixaController {

    @Autowired
    CaixaRepository repository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity init(@PathVariable(value = "id") Long id) {
        Optional<Caixa> caixa = repository.findById(id);
        return new ResponseEntity<Caixa>(caixa.get(), HttpStatus.OK);
    }

    @GetMapping(value = {"/page/{page}", "/page/{page}/busca/{param}"}, produces = "application/json")
    public ResponseEntity<Page<Caixa>> list(@PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "param", required = false) String param) {

        PageRequest pageRequest = PageRequest.of(page-1, 5, Sort.by("id"));

        Page<Caixa> list = null;

        if(param != null) {
            list = repository.findByParam(param, pageRequest);
        }else{
            list = repository.findAll(pageRequest);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/busca/{param}"}, produces = "application/json")
    public ResponseEntity<List<Caixa>> listByAll(@PathVariable(value = "param", required = false) LocalDateTime param) {
        List<Caixa> list = (List<Caixa>) repository.findByParam(param);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = {"/aberto"}, produces = "application/json")
    public ResponseEntity<Caixa> getOpen() {
        Caixa caixa = repository.getOpen();
        return new ResponseEntity<>(caixa, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Caixa> store(@RequestBody Caixa caixa) {
        Caixa caixaStorade = repository.save(caixa);
        return new ResponseEntity<>(caixaStorade, HttpStatus.OK);
    }

    @PutMapping(value = "", produces = "application/json")
    public ResponseEntity<Caixa> update(@RequestBody Caixa caixa) {
        Caixa caixaStored = repository.save(caixa);
        return new ResponseEntity<Caixa>(caixaStored, HttpStatus.OK);
    }

    @PostMapping(value = "/fechar", produces = "application/json")
    public ResponseEntity<Caixa> fechar(@RequestBody Caixa caixaParam) {

        Optional<Caixa> caixaOp = repository.findById(caixaParam.getId());
        Caixa caixa = caixaOp.get();

        caixa.setDataFechamento(caixaParam.getDataFechamento());
        caixa.setQuebra(caixaParam.getQuebra());

        Caixa caixaStorade = repository.save(caixa);
        return new ResponseEntity<>(caixaStorade, HttpStatus.OK);
    }
}
