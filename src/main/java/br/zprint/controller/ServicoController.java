package br.zprint.controller;

import br.zprint.dto.ServicoDTO;
import br.zprint.model.Conta;
import br.zprint.model.Equipamento;
import br.zprint.model.Pessoa;
import br.zprint.model.Servico;
import br.zprint.repository.*;
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

    @Autowired
    ContaRepository contaRepository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ServicoDTO> init(@PathVariable(value = "id") Long id) {
        Optional<Servico> servico = repository.findById(id);
        ServicoDTO servicoDto = new ServicoDTO(servico.get());

        return new ResponseEntity<>(servicoDto, HttpStatus.OK);
    }

    @GetMapping(value = {"/page/{page}", "/page/{page}/busca/{param}"}, produces = "application/json")
    public ResponseEntity<Page<ServicoDTO>> list(@PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "param", required = false) String param) {

        PageRequest pageRequest = PageRequest.of(page-1, 5);

        Page<ServicoDTO> list;

        if(param != null) {
            list = repository.findByParam(param.toUpperCase(), pageRequest);
        }else{
            list = repository.findAllDTO(pageRequest);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/busca/{param}"}, produces = "application/json")
    public ResponseEntity<List<Servico>> listByAll(@PathVariable(value = "param", required = false) String param) {
        List<Servico> list = (List<Servico>) repository.findByParam(param);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity store(@RequestBody Servico servico) {

        itemRepository.deletByServico(servico.getId());

        /** Esse carai não montou o objeto corretamente, então estamos salvando cada entidade separada */
        if(servico.getPessoa() != null) {
            if(servico.getPessoa().getId() == null) {
                Pessoa pessoaStorade = pessoaRepository.save(servico.getPessoa());
                servico.setPessoa(pessoaStorade);
            }
        }

        /** Equipamento */
        if(servico.getEquipamento() != null) {
            if(servico.getEquipamento().getId() == null){
                servico.getEquipamento().setPessoa(servico.getPessoa());
                Equipamento equipamentoStorade = equipamentoRepository.save(servico.getEquipamento());

                servico.setEquipamento(equipamentoStorade);
            }
        }

        /**
         * Contas
         */

        if(servico.getContas() != null) {
            for (int i = 0; i < servico.getContas().size(); i++) {
                if(servico.getContas().get(i).getId() != null) {
                    Optional<Conta> contasOpt = contaRepository.findById(servico.getContas().get(i).getId());
                    servico.getContas().set(i, contasOpt.get());
                }
            }
        }

        for (int i = 0; i < servico.getItensServico().size(); i++) {
            servico.getItensServico().get(i).setServico(servico);
        }

        Servico servicoStorade = repository.save(servico);

        return new ResponseEntity<>(new ServicoDTO(servicoStorade), HttpStatus.OK);
    }
}
