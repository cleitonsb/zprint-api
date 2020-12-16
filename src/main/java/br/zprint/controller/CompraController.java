package br.zprint.controller;

import br.zprint.model.Compra;
import br.zprint.model.Venda;
import br.zprint.repository.CompraItemRepository;
import br.zprint.repository.CompraRepository;
import br.zprint.repository.VendaItemRepository;
import br.zprint.repository.VendaRepository;
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
@RequestMapping(value = "/compra")
public class CompraController {

    @Autowired
    CompraRepository repository;

    @Autowired
    CompraItemRepository itemRepository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity init(@PathVariable(value = "id") Long id) {
        Optional<Compra> compra = repository.findById(id);
        return new ResponseEntity<>(compra.get(), HttpStatus.OK);
    }

    @GetMapping(value = {"/page/{page}", "/page/{page}/busca/{param}"}, produces = "application/json")
    public ResponseEntity<Page<Compra>> list(@PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "param", required = false) String param) {

        PageRequest pageRequest = PageRequest.of(page-1, 5, Sort.by("id"));

        Page<Compra> list;

        if(param != null) {
            list = repository.findByParam(param, pageRequest);
        }else{
            list = repository.findAll(pageRequest);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Compra> store(@RequestBody Compra compra) {

        itemRepository.deletByCompra(compra.getId());

        for (int i = 0; i < compra.getItensCompra().size(); i++) {
            compra.getItensCompra().get(i).setCompra(compra);
        }

        Compra compraStorade = repository.save(compra);
        return new ResponseEntity<>(compraStorade, HttpStatus.OK);
    }

    @PostMapping(value = "/update", produces = "application/json")
    public ResponseEntity<Compra> update(@RequestBody Compra compraParam) {
        Optional<Compra> compraOpt = repository.findById(compraParam.getId());
        Compra compra = compraOpt.get();

        Compra compraStorade = repository.save(compra);
        return new ResponseEntity<>(compraStorade, HttpStatus.OK);
    }


}
