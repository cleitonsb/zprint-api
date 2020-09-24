package br.zprint.repository;

import br.zprint.model.Produto;
import br.zprint.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    @Query("select p from Produto p where p.situacao = true and (p.nome like %?1% or p.ean like %?1%)")
    Iterable<Produto> findByParam(String param);

    @Query("select p from Produto p where p.situacao = true and (p.nome like %?1% or p.ean like %?1%)")
    Page<Produto> findByParam(String param, Pageable page);

    @Query("select p from Produto p where p.situacao = true")
    Page<Produto> findAll(Pageable page);

    @Modifying
    @Query("update Produto p set p.situacao = false where p.id = ?1")
    void softDelete(Long id);
}
