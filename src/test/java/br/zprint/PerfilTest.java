package br.zprint;

import br.zprint.model.Perfil;
import br.zprint.model.Permissao;
import br.zprint.repository.PerfilRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

//        permissoes.add(new Permissao("Usuário", "/usuario", "GET", "usuario_get"));
//        permissoes.add(new Permissao("Usuário", "/usuario", "POST", "usuario_post"));
//        permissoes.add(new Permissao("Usuário", "/usuario", "PUT", "usuario_put"));
//        permissoes.add(new Permissao("Usuário", "/usuario", "DELETE", "usuario_delete"));
//        permissoes.add(new Permissao("Perfil", "/perfil", "GET", "perfil_get"));
//        permissoes.add(new Permissao("Perfil", "/perfil", "POST", "perfil_post"));
//        permissoes.add(new Permissao("Perfil", "/perfil", "PUT", "perfil_put"));
//        permissoes.add(new Permissao("Perfil", "/perfil", "DELETE", "perfil_delete"));
        permissoes.add(new Permissao("Produto", "/produto", "GET", "produto_get"));
        permissoes.add(new Permissao("Produto", "/produto", "POST", "produto_post"));
        permissoes.add(new Permissao("Produto", "/produto", "PUT", "produto_put"));
        permissoes.add(new Permissao("Produto", "/produto", "DELETE", "produto_delete"));
        //permissoes.add(new Permissao("", "", "", ""));

        return permissoes;
    }

    @Test
    public void update(){
        Optional<Perfil> perfil = repository.findById(1L);
        Perfil perfilStored = perfil.get();

        List<Permissao> permissaos = perfilStored.getPermissoes();
        permissaos.addAll(this.permissoes());

        perfilStored.setPermissoes(permissaos);

        repository.save(perfilStored);
    }
}
