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
        Perfil adm = new Perfil();
        adm.setId(1L);
        adm.setNome("Administrador");
        adm.setPermissoes(this.permissoesAdm());

        repository.save(adm);

        Perfil atendente = new Perfil();
        atendente.setId(2L);
        atendente.setNome("Atendente");
        atendente.setPermissoes(this.permissoesAtendente());

        repository.save(atendente);

        Perfil caixa = new Perfil();
        caixa.setId(3L);
        caixa.setNome("Caixa");
        caixa.setPermissoes(this.permissoesCaixa());

        repository.save(caixa);
    }

    public List<Permissao> permissoesAdm() {
        List<Permissao> permissoes = new ArrayList<Permissao>();

        permissoes.add(new Permissao("Usuário", "/usuario", "GET", "usuario_get"));
        permissoes.add(new Permissao("Usuário", "/usuario", "POST", "usuario_post"));
        permissoes.add(new Permissao("Usuário", "/usuario", "PUT", "usuario_put"));
        permissoes.add(new Permissao("Usuário", "/usuario", "DELETE", "usuario_delete"));
        permissoes.add(new Permissao("Produto", "/produto", "GET", "produto_get"));
        permissoes.add(new Permissao("Produto", "/produto", "POST", "produto_post"));
        permissoes.add(new Permissao("Produto", "/produto", "PUT", "produto_put"));
        permissoes.add(new Permissao("Produto", "/produto", "DELETE", "produto_delete"));
        permissoes.add(new Permissao("Venda", "/venda", "GET", "venda_get"));
        permissoes.add(new Permissao("Venda", "/venda", "POST", "venda_post"));
        permissoes.add(new Permissao("Venda", "/venda", "PUT", "venda_put"));
        permissoes.add(new Permissao("Venda", "/venda", "DELETE", "venda_delete"));
        permissoes.add(new Permissao("Caixa", "/caixa", "GET", "caixa_get"));
        permissoes.add(new Permissao("Caixa", "/caixa", "POST", "caixa_post"));
        permissoes.add(new Permissao("Caixa", "/caixa", "PUT", "caixa_put"));
        permissoes.add(new Permissao("Caixa", "/caixa", "DELETE", "caixa_delete"));

        permissoes.add(new Permissao("Pagamento", "/pagamento", "GET", "pagamento_get"));
        permissoes.add(new Permissao("Pagamento", "/pagamento", "POST", "pagamento_post"));
        permissoes.add(new Permissao("Pagamento", "/pagamento", "PUT", "pagamento_put"));
        permissoes.add(new Permissao("Pagamento", "/pagamento", "DELETE", "pagamento_delete"));

        permissoes.add(new Permissao("Conta", "/conta", "GET", "conta_get"));
        permissoes.add(new Permissao("Conta", "/conta", "POST", "conta_post"));
        permissoes.add(new Permissao("Conta", "/conta", "PUT", "conta_put"));
        permissoes.add(new Permissao("Conta", "/conta", "DELETE", "conta_delete"));

//        permissoes.add(new Permissao("Compra", "/compra", "GET", "compra_get"));
//        permissoes.add(new Permissao("Compra", "/compra", "POST", "compra_post"));
//        permissoes.add(new Permissao("Compra", "/compra", "PUT", "compra_put"));
//        permissoes.add(new Permissao("Compra", "/compra", "DELETE", "compra_delete"));

        permissoes.add(new Permissao("Pessoa", "/pessoa", "GET", "pessoa_get"));
        permissoes.add(new Permissao("Pessoa", "/pessoa", "POST", "pessoa_post"));
        permissoes.add(new Permissao("Pessoa", "/pessoa", "PUT", "pessoa_put"));
        permissoes.add(new Permissao("Pessoa", "/pessoa", "DELETE", "pessoa_delete"));

        permissoes.add(new Permissao("Serviço", "/servico", "GET", "servico_get"));
        permissoes.add(new Permissao("Serviço", "/servico", "POST", "servico_post"));
        permissoes.add(new Permissao("Serviço", "/servico", "PUT", "servico_put"));
        permissoes.add(new Permissao("Serviço", "/servico", "DELETE", "servico_delete"));

        //permissoes.add(new Permissao("", "", "", ""));

        return permissoes;
    }

    public List<Permissao> permissoesCaixa() {
        List<Permissao> permissoes = new ArrayList<Permissao>();

        permissoes.add(new Permissao("Produto", "/produto", "GET", "produto_get"));
        permissoes.add(new Permissao("Produto", "/produto", "POST", "produto_post"));
        permissoes.add(new Permissao("Produto", "/produto", "PUT", "produto_put"));
        permissoes.add(new Permissao("Produto", "/produto", "DELETE", "produto_delete"));

        permissoes.add(new Permissao("Venda", "/venda", "GET", "venda_get"));
        permissoes.add(new Permissao("Venda", "/venda", "POST", "venda_post"));
        permissoes.add(new Permissao("Venda", "/venda", "PUT", "venda_put"));
        permissoes.add(new Permissao("Venda", "/venda", "DELETE", "venda_delete"));

        permissoes.add(new Permissao("Caixa", "/caixa", "GET", "caixa_get"));
        permissoes.add(new Permissao("Caixa", "/caixa", "POST", "caixa_post"));
        permissoes.add(new Permissao("Caixa", "/caixa", "PUT", "caixa_put"));
        permissoes.add(new Permissao("Caixa", "/caixa", "DELETE", "caixa_delete"));

        permissoes.add(new Permissao("Conta", "/conta", "GET", "conta_get"));
        permissoes.add(new Permissao("Conta", "/conta", "POST", "conta_post"));
        permissoes.add(new Permissao("Conta", "/conta", "PUT", "conta_put"));
        permissoes.add(new Permissao("Conta", "/conta", "DELETE", "conta_delete"));

        permissoes.add(new Permissao("Pessoa", "/pessoa", "GET", "pessoa_get"));
        permissoes.add(new Permissao("Pessoa", "/pessoa", "POST", "pessoa_post"));
        permissoes.add(new Permissao("Pessoa", "/pessoa", "PUT", "pessoa_put"));
        permissoes.add(new Permissao("Pessoa", "/pessoa", "DELETE", "pessoa_delete"));

        return permissoes;
    }

    public List<Permissao> permissoesAtendente() {
        List<Permissao> permissoes = new ArrayList<Permissao>();

        permissoes.add(new Permissao("Produto", "/produto", "GET", "produto_get"));
        permissoes.add(new Permissao("Produto", "/produto", "POST", "produto_post"));
        permissoes.add(new Permissao("Produto", "/produto", "PUT", "produto_put"));
        permissoes.add(new Permissao("Produto", "/produto", "DELETE", "produto_delete"));

        permissoes.add(new Permissao("Venda", "/venda", "GET", "venda_get"));
        permissoes.add(new Permissao("Venda", "/venda", "POST", "venda_post"));
        permissoes.add(new Permissao("Venda", "/venda", "PUT", "venda_put"));
        permissoes.add(new Permissao("Venda", "/venda", "DELETE", "venda_delete"));

        permissoes.add(new Permissao("Pessoa", "/pessoa", "GET", "pessoa_get"));
        permissoes.add(new Permissao("Pessoa", "/pessoa", "POST", "pessoa_post"));
        permissoes.add(new Permissao("Pessoa", "/pessoa", "PUT", "pessoa_put"));
        permissoes.add(new Permissao("Pessoa", "/pessoa", "DELETE", "pessoa_delete"));

        return permissoes;
    }

    @Test
    public void update(){
        Optional<Perfil> perfil = repository.findById(1L);
        Perfil perfilStored = perfil.get();

        List<Permissao> permissaos = perfilStored.getPermissoes();
        permissaos.addAll(this.permissoesAdm());

        perfilStored.setPermissoes(permissaos);

        repository.save(perfilStored);
    }
}
