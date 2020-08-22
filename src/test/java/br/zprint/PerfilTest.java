package br.zprint;

import br.zprint.model.Perfil;
import br.zprint.model.Permissao;
import br.zprint.repository.PerfilRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PerfilTest {

    @Autowired
    PerfilRepository repository;

    @Test
    public void perfilStore() {
        Perfil perfil = new Perfil();

        perfil.setNome("Administrador");
        perfil.setPermissoes(this.permissoes());

        repository.save(perfil);
    }

    public List<Permissao> permissoes() {
        List<Permissao> permissoes = new ArrayList<Permissao>();

        permissoes.add(new Permissao("Usuário", "/user", "GET"));
        permissoes.add(new Permissao("Usuário", "/user", "POST"));
        permissoes.add(new Permissao("Usuário", "/user", "PUT"));
        permissoes.add(new Permissao("Usuário", "/user", "DELETE"));
        permissoes.add(new Permissao("Perfil", "/perfil", "GET"));
        permissoes.add(new Permissao("Perfil", "/perfil", "POST"));
        permissoes.add(new Permissao("Perfil", "/perfil", "PUT"));
        permissoes.add(new Permissao("Perfil", "/perfil", "DELETE"));

        return permissoes;
    }
}
