package br.zprint;

import br.zprint.model.Perfil;
import br.zprint.model.Usuario;
import br.zprint.repository.PerfilRepository;
import br.zprint.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class UsuarioTest {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PerfilRepository perfilRepository;

    @Test
    public void usuarioStore(){

        Optional<Perfil> perfil = perfilRepository.findById(13L);

        Usuario usuario = new Usuario();
        usuario.setNome("Cleiton");
        usuario.setEmail("cleiton@admin.com");
        usuario.setCelular("61984624081");
        usuario.setSenha(new BCryptPasswordEncoder().encode("123"));
        usuario.setTelefone("6134626333");
        usuario.setPerfil(perfil.get());

        repository.save(usuario);
    }

    @Test
    public void geraTimestamp() {
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date(System.currentTimeMillis()));
    }
}
