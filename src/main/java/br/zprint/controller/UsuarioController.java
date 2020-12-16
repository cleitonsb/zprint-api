package br.zprint.controller;

import br.zprint.dto.UsuarioDTO;
import br.zprint.model.Usuario;
import br.zprint.repository.PerfilRepository;
import br.zprint.repository.UsuarioRepository;
import br.zprint.service.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    UploadImageService uploadImageService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity init(@PathVariable(value = "id") Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/{email}/email", produces = "application/json")
    public ResponseEntity userByEmail(@PathVariable(value = "email") String email) {
        Usuario usuario = repository.findByLogin(email);
        return new ResponseEntity<UsuarioDTO>(new UsuarioDTO(usuario), HttpStatus.OK);
    }

    @GetMapping(value = {"/page/{page}", "/page/{page}/busca/{param}"}, produces = "application/json")
    public ResponseEntity<Page<Usuario>> list(@PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "param", required = false) String param) {

        PageRequest pageRequest = PageRequest.of(page-1, 5, Sort.by("nome"));

        Page<Usuario> list = null;

        if(param != null) {
            list = repository.findByParam(param, pageRequest);
        }else{
            list = repository.findAll(pageRequest);
        }

        return new ResponseEntity<Page<Usuario>>(list, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/busca/{param}"}, produces = "application/json")
    public ResponseEntity<List<Usuario>> listByAll(@PathVariable(value = "param", required = false) String param) {
        param = (param != null) ? param.toUpperCase() : "";

        List<Usuario> list = (List<Usuario>) repository.findByParam(param);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity upload(@RequestParam(name = "avatar", required = true) MultipartFile avatar, @RequestParam("usuarioid") Long usuarioId) {

        Optional<Usuario> usuarioOpt = repository.findById(usuarioId);
        Usuario usuario = usuarioOpt.get();

        String target = uploadImageService.uploadFile(avatar, usuario);

        usuario.setAvatar(target);
        repository.save(usuario);

        return new ResponseEntity<Usuario>(HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<Usuario> store(@RequestBody Usuario usuario) {

//        if(usuario.getPerfil() == null) {
//            Optional<Perfil> perfil = perfilRepository.findById(10L);
//            usuario.setPerfil(perfil.get());
//        }

//        for (int i = 0; i < usuario.getEnderecos().size(); i++) {
//            if(usuario.getEnderecos().get(i).getCep() == null) {
//                usuario.getEnderecos().remove(usuario.getEnderecos().get(i));
//            }else{
//                usuario.getEnderecos().get(i).setUsuario(usuario);
//            }
//        }

        Usuario usuarioStored = repository.save(usuario);
        return new ResponseEntity<Usuario>(usuarioStored, HttpStatus.OK);
    }

    @PutMapping(value = "", produces = "application/json")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
        Usuario usuarioStored = repository.save(usuario);
        return new ResponseEntity<Usuario>(usuarioStored, HttpStatus.OK);
    }

    @GetMapping(value = "/avatar/{nomeArquivo}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable(value = "nomeArquivo") String nomeArquivo) throws Exception {
        try {
            byte[] bytes = StreamUtils.copyToByteArray((uploadImageService.getFile(nomeArquivo + ".jpg")).getInputStream());

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
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
