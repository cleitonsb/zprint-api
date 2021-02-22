package br.zprint.controller;

import br.zprint.dto.EquipamentoDTO;
import br.zprint.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/equipamento")
public class EquipamentoController {

    @Autowired
    EquipamentoRepository repository;

    @GetMapping(value = "/{idPessoa}", produces = "application/json")
    public ResponseEntity init(@PathVariable(value = "idPessoa") Long idPessoa) {
        List<EquipamentoDTO> listEquipamentos = repository.findByPessoa(idPessoa);
        return new ResponseEntity<>(listEquipamentos, HttpStatus.OK);
    }

}
