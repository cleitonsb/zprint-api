package br.zprint.controller;

import br.zprint.dto.UsuarioDTO;
import br.zprint.model.Perfil;
import br.zprint.model.Usuario;
import br.zprint.model.Venda;
import br.zprint.repository.PerfilRepository;
import br.zprint.repository.UsuarioRepository;
import br.zprint.repository.VendaItemRepository;
import br.zprint.repository.VendaRepository;
import br.zprint.service.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/venda")
public class VendaController {

    @Autowired
    VendaRepository repository;

    @Autowired
    VendaItemRepository itemRepository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity init(@PathVariable(value = "id") Long id) {
        Optional<Venda> venda = repository.findById(id);
        return new ResponseEntity<>(venda.get(), HttpStatus.OK);
    }

    @GetMapping(value = {"/page/{page}", "/page/{page}/busca/{param}"}, produces = "application/json")
    public ResponseEntity<Page<Venda>> list(@PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "param", required = false) String param) {

        PageRequest pageRequest = PageRequest.of(page-1, 5, Sort.by("nome"));

        Page<Venda> list;

        if(param != null) {
            list = repository.findByParam(param, pageRequest);
        }else{
            list = repository.findAll(pageRequest);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/busca/{param}"}, produces = "application/json")
    public ResponseEntity<List<Venda>> listByAll(@PathVariable(value = "param", required = false) String param) {

        List<Venda> list;
        if (param.equals("noCaixa")) {
            list = (List<Venda>) repository.findOpen();
        }else{
            list = (List<Venda>) repository.findByParam(param);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/caixa/{idCaixa}"}, produces = "application/json")
    public ResponseEntity<List<Venda>> listByCaixa(@PathVariable(value = "idCaixa", required = false) Long idCaixa) {
        List<Venda> list = (List<Venda>) repository.findByCaixa(idCaixa);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Venda> store(@RequestBody Venda venda) {

        itemRepository.deletByVenda(venda.getId());

        for (int i = 0; i < venda.getItensVenda().size(); i++) {
            venda.getItensVenda().get(i).setVenda(venda);
        }

        for (int i = 0; i < venda.getPagamentos().size(); i++) {
            venda.getPagamentos().get(i).setVenda(venda);
        }

        Venda vendaStorade = repository.save(venda);
        return new ResponseEntity<>(vendaStorade, HttpStatus.OK);
    }

    @PostMapping(value = "/update", produces = "application/json")
    public ResponseEntity<Venda> update(@RequestBody Venda vendaParam) {
        Optional<Venda> vendaOpt = repository.findById(vendaParam.getId());
        Venda venda = vendaOpt.get();
        venda.setCaixa(vendaParam.getCaixa());
        venda.setPagamentos(vendaParam.getPagamentos());
        venda.setTroco(vendaParam.getTroco());

        for (int i = 0; i < venda.getPagamentos().size(); i++) {
            venda.getPagamentos().get(i).setVenda(venda);
        }

        Venda vendaStorade = repository.save(venda);
        return new ResponseEntity<>(vendaStorade, HttpStatus.OK);
    }


}
