package br.zprint.controller;

import br.zprint.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pagamento")
public class PagamentoController {

    @Autowired
    PagamentoRepository repository;

    @GetMapping(value = "/{idCaixa}", produces = "application/json")
    public ResponseEntity init(@PathVariable(value = "idCaixa") Long idCaixa) {
        List listPagamentos = repository.findByCaixa(idCaixa);
        return new ResponseEntity<>(listPagamentos, HttpStatus.OK);
    }

}
