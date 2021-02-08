package br.zprint;

import br.zprint.controller.ServicoController;
import br.zprint.model.*;
import br.zprint.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ServicoTest {

    @Autowired
    ServicoRepository repository;

    @Autowired
    ServicoItemRepository itemRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EquipamentoRepository equipamentoRepository;




    @Test
    public void store(){

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Antonio Freitas");
        pessoa.setCelular("(61) 984624081");

        Pessoa pessoaStored = pessoaRepository.save(pessoa);

        Equipamento equipamento = new Equipamento();
        equipamento.setMarca("Dell");
        equipamento.setDescricao("Proc 17");
        equipamento.setSerie("00011");
        equipamento.setModelo("Notebook");
        equipamento.setPessoa(pessoaStored);

        Equipamento equipamentoStored = equipamentoRepository.save(equipamento);

        Optional<Produto> produtoOp1 = produtoRepository.findById(3L);

        Optional<Usuario> usuario = usuarioRepository.findById(32L);

        Servico servico = new Servico();

        servico.setPessoa(pessoaStored);
        servico.setResponsavel(usuario.get());
        servico.setUsuario(usuario.get());
        servico.setData(new Timestamp(System.currentTimeMillis()));
        servico.setDesconto(10.0);
        servico.setTotal(100.00);
        servico.setEquipamento(equipamentoStored);

        Servico servicoStored = repository.save(servico);


        ServicoItem item = new ServicoItem();
        item.setProduto(produtoOp1.get());
        item.setQt(5.0);
        item.setPreco(100.00);
        item.setServico(servicoStored);

        ServicoItem item2 = new ServicoItem();
        item2.setProduto(produtoOp1.get());
        item2.setQt(6.0);
        item2.setPreco(106.00);
        item2.setServico(servicoStored);

        itemRepository.save(item);
        itemRepository.save(item2);

    }


}
