package br.zprint;

import br.zprint.model.Cidade;
import br.zprint.model.Endereco;
import br.zprint.model.Perfil;
import br.zprint.model.Usuario;
import br.zprint.repository.CidadeRepository;
import br.zprint.repository.PerfilRepository;
import br.zprint.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsuarioTest {

//    @Autowired
//    UsuarioRepository repository;
//
//    @Autowired
//    PerfilRepository perfilRepository;
//
//    @Autowired
//    CidadeRepository cidadeRepository;

//    @Test
//    public void usuarioStore(){
//        Usuario usuario = new Usuario();
//
//        Optional<Cidade> cidade = cidadeRepository.findById(1L);
//
//        Endereco endereco = new Endereco();
//        endereco.setBairro("Asa Norte");
//        endereco.setCep("70767020");
//        endereco.setLogradouro("SQN");
//        endereco.setNumero("615");
//        endereco.setCidade(cidade.get());
//        //endereco.setUsuario(usuario);
//
//        List<Endereco> enderecos = new ArrayList<>();
//        enderecos.add(endereco);
//
//        Optional<Perfil> perfil = perfilRepository.findById(1L);
//
//        usuario.setNome("Cleiton 9");
//        usuario.setEmail("cleiton9@admin.com");
//        usuario.setCelular("61984624081");
//        usuario.setSenha(new BCryptPasswordEncoder().encode("123"));
//        usuario.setTelefone("6134626333");
//        usuario.setEnderecos(enderecos);
//        usuario.setPerfil(perfil.get());
//
//        repository.save(usuario);
//    }
//
//    @Test
//    public void geraTimestamp() {
//        System.out.println(System.currentTimeMillis());
//        System.out.println(new Date(System.currentTimeMillis()));
//    }
}
