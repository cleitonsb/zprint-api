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

        Perfil perfil2 = new Perfil();
        perfil2.setId(2L);
        perfil2.setNome("Usuário");

        repository.save(perfil2);
    }

    public List<Permissao> permissoes() {
        List<Permissao> permissoes = new ArrayList<Permissao>();

        permissoes.add(new Permissao("Usuário", "/usuario", "GET", "usuario_get"));
        permissoes.add(new Permissao("Usuário", "/usuario", "POST", "usuario_post"));
        permissoes.add(new Permissao("Usuário", "/usuario", "PUT", "usuario_put"));
        permissoes.add(new Permissao("Usuário", "/usuario", "DELETE", "usuario_delete"));
        permissoes.add(new Permissao("Perfil", "/perfil", "GET", "perfil_get"));
        permissoes.add(new Permissao("Perfil", "/perfil", "POST", "perfil_post"));
        permissoes.add(new Permissao("Perfil", "/perfil", "PUT", "perfil_put"));
        permissoes.add(new Permissao("Perfil", "/perfil", "DELETE", "perfil_delete"));
        //permissoes.add(new Permissao("", "", "", ""));

        return permissoes;
    }
}
