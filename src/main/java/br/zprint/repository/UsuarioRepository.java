package br.zprint.repository;

import br.zprint.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where u.email = ?1")
    Usuario findByLogin(String login);

    @Query("select u from Usuario u where u.email like %?1% or u.nome like %?1% or u.celular like %?1% or u.telefone like %?1%")
    Iterable<Usuario> findByParam(String param);

    @Query("select u from Usuario u where u.email like %?1% or u.nome like %?1% or u.celular like %?1% or u.telefone like %?1%")
    Page<Usuario> findByParam(String param, Pageable page);
}
