package br.zprint.repository;

import br.zprint.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("select p from Pessoa p where (p.email like %?1% or p.nome like %?1% or p.celular like %?1% or p.telefone like %?1%) and p.situacao = true")
    Iterable<Pessoa> findByParam(String param);

    @Query("select p from Pessoa p where (p.email like %?1% or p.nome like %?1% or p.celular like %?1% or p.telefone like %?1%) and p.situacao = true")
    Page<Pessoa> findByParam(String param, Pageable page);

    @Query("select p from Pessoa p where p.situacao = true")
    Page<Pessoa> findAll(Pageable page);

    @Modifying
    @Query("update Pessoa p set p.situacao = false where p.id = ?1")
    void softDelete(Long id);
}
