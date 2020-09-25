package br.zprint.repository;

import br.zprint.model.Compra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CompraRepository extends CrudRepository<Compra, Long> {

    @Query("select c from Compra c where (c.data = ?1) and c.situacao = true")
    Iterable<Compra> findByParam(String param);

    @Query("select c from Compra c where (c.data = ?1) and c.situacao = true")
    Page<Compra> findByParam(String param, Pageable page);

    @Query("select c from Compra c where c.situacao = true")
    Page<Compra> findAll(Pageable page);
}
