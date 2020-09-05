package br.zprint.repository;

import br.zprint.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where u.email = ?1")
    Usuario findByLogin(String login);

    @Query("select u from Usuario u where (u.email like %?1% or u.nome like %?1% or u.celular like %?1% or u.telefone like %?1%) and u.situacao = true")
    Iterable<Usuario> findByParam(String param);

    @Query("select u from Usuario u where (u.email like %?1% or u.nome like %?1% or u.celular like %?1% or u.telefone like %?1%) and u.situacao = true")
    Page<Usuario> findByParam(String param, Pageable page);

    @Query("select u from Usuario u where u.situacao = true")
    Page<Usuario> findAll(Pageable page);

    @Modifying
    @Query("update Usuario u set u.situacao = false where u.id = ?1")
    void softDelete(Long id);
}
