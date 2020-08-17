package br.zprint;

import br.zprint.model.Usuario;
import br.zprint.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UsuarioTests {

    @Autowired
    UsuarioRepository repository;

    @Test
    public void usuarioStore(){
        Usuario usuario = new Usuario();
        usuario.setNome("Cleiton 3");
        usuario.setEmail("cleiton@admin.com");
        usuario.setCelular("61984624081");
        usuario.setSenha("123");
        usuario.setTelefone("6134626333");

        repository.save(usuario);
    }

    @Test
    public void geraTimestamp() {
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date(System.currentTimeMillis()));
    }
}
