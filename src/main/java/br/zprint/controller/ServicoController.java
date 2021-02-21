package br.zprint.controller;

import br.zprint.model.Equipamento;
import br.zprint.model.Pessoa;
import br.zprint.model.Servico;
import br.zprint.repository.EquipamentoRepository;
import br.zprint.repository.PessoaRepository;
import br.zprint.repository.ServicoItemRepository;
import br.zprint.repository.ServicoRepository;
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
@RequestMapping(value = "/servico")
public class ServicoController {

    @Autowired
    ServicoRepository repository;

    @Autowired
    ServicoItemRepository itemRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    EquipamentoRepository equipamentoRepository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity init(@PathVariable(value = "id") Long id) {
        Optional<Servico> servico = repository.findById(id);
        return new ResponseEntity<>(servico.get(), HttpStatus.OK);
    }

    @GetMapping(value = {"/page/{page}", "/page/{page}/busca/{param}"}, produces = "application/json")
    public ResponseEntity<Page<Servico>> list(@PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "param", required = false) String param) {

        PageRequest pageRequest = PageRequest.of(page-1, 5);

        Page<Servico> list;

        if(param != null) {
            list = repository.findByParam(param.toUpperCase(), pageRequest);
        }else{
            list = repository.findAll(pageRequest);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/busca/{param}"}, produces = "application/json")
    public ResponseEntity<List<Servico>> listByAll(@PathVariable(value = "param", required = false) String param) {
        List<Servico> list = (List<Servico>) repository.findByParam(param);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Servico> store(@RequestBody Servico servico) {

        itemRepository.deletByServico(servico.getId());

        for (int i = 0; i < servico.getPessoa().getEquipamentos().size(); i++) {
            servico.getPessoa().getEquipamentos().get(i).setPessoa(servico.getPessoa());
        }

        /** Esse carai não montou o objeto corretamente, então estamos salvando cada entidade separada */
//        Pessoa pessoaStorade = pessoaRepository.save(servico.getPessoa());
//        servico.setPessoa(pessoaStorade);

        /** Equipamento */
//        if(servico.getEquipamento() != null) {
//            if(servico.getEquipamento().getModelo() != null) {
//                servico.setEquipamento(equipamentoRepository.save(servico.getEquipamento()));
//            }
//        }

        for (int i = 0; i < servico.getItensServico().size(); i++) {
            servico.getItensServico().get(i).setServico(servico);
        }

        Servico servicoStorade = repository.save(servico);


        return new ResponseEntity<>(servicoStorade, HttpStatus.OK);
    }


}
