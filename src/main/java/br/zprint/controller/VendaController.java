package br.zprint.controller;

import br.zprint.model.Usuario;
import br.zprint.model.Venda;
import br.zprint.repository.UsuarioRepository;
import br.zprint.repository.VendaItemRepository;
import br.zprint.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/venda")
public class VendaController {

    @Autowired
    VendaRepository repository;

    @Autowired
    VendaItemRepository itemRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

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
        List<Venda> list = (List<Venda>) repository.findByParam(param);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Venda> store(@RequestBody Venda venda) {

        itemRepository.deletByVenda(venda.getId());

        for (int i = 0; i < venda.getItensVenda().size(); i++) {
            venda.getItensVenda().get(i).setVenda(venda);
        }

        Venda vendaStorade = repository.save(venda);
        return new ResponseEntity<>(vendaStorade, HttpStatus.OK);
    }

    @PostMapping(value = "/remove", produces = "application/json")
    public ResponseEntity remove(@RequestParam("id") Long id,
                                 @RequestParam("usuario") String usuario,
                                 @RequestParam("senha") String senha) {
        try{
            Usuario user = usuarioRepository.findByLogin(usuario);

            if(!Objects.isNull(user)){
                if(new BCryptPasswordEncoder().matches(senha, user.getSenha())) {
                    repository.softDelete(id);
                    return new ResponseEntity(HttpStatus.OK);
                }
            }

            return new ResponseEntity<>("Não permitido para esse usuário", HttpStatus.FORBIDDEN);
        }catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }


}
