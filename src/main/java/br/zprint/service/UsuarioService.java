package br.zprint.service;

import br.zprint.model.Usuario;
import br.zprint.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = repository.findByLogin(email);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new User(usuario.getEmail(), usuario.getPassword(), usuario.getAuthorities());
    }
}
