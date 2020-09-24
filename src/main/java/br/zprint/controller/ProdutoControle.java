package br.zprint.controller;

import br.zprint.model.Perfil;
import br.zprint.model.Produto;
import br.zprint.model.Usuario;
import br.zprint.repository.ProdutoRepository;
import javassist.NotFoundException;
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
@RequestMapping(value = "/produto")
public class ProdutoControle {

    @Autowired
    ProdutoRepository repository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Produto> init(@PathVariable(value = "id") Long id) {
        try {
            Optional<Produto> produto = repository.findById(id);

            if(produto.isEmpty() == true) {
                throw new NotFoundException("Registro não encontrado");
            }
            return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = {"/page/{page}", "/page/{page}/busca/{param}"}, produces = "application/json")
    public ResponseEntity<Page<Produto>> list(@PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "param", required = false) String param) {

        PageRequest pageRequest = PageRequest.of(page-1, 5, Sort.by("nome"));

        Page<Produto> list = null;

        if(param != null) {
            list = repository.findByParam(param, pageRequest);
        }else{
            list = repository.findAll(pageRequest);
        }

        return new ResponseEntity<Page<Produto>>(list, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/busca/{param}"}, produces = "application/json")
    public ResponseEntity<List<Produto>> listAll(@PathVariable(value = "param", required = false) String param) {
        param = (param != null) ? param.toUpperCase() : "";

        List<Produto> list = (List<Produto>) repository.findByParam(param);
        return new ResponseEntity<List<Produto>>(list, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Produto> store(@RequestBody Produto produto) {
        Produto produtoStored = repository.save(produto);
        return new ResponseEntity<>(produtoStored, HttpStatus.OK);
    }

    @PutMapping(value = "", produces = "application/json")
    public ResponseEntity<Produto> update(@RequestBody Produto produto) {
        Produto produtoStored = repository.save(produto);
        return new ResponseEntity<>(produtoStored, HttpStatus.OK);
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
