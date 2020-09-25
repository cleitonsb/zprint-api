package br.zprint.repository;

import br.zprint.model.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ContaRepository extends CrudRepository<Conta, Long> {

    @Query("select c from Conta c where (c.valor = ?1 or c.data = ?1) and c.situacao = true")
    Iterable<Conta> findByParam(String param);

    @Query("select c from Conta c where (c.valor = ?1 or c.data = ?1) and c.situacao = true")
    Page<Conta> findByParam(String param, Pageable page);

    @Query("select c from Conta c where c.situacao = true order by c.id desc")
    Page<Conta> findAll(Pageable page);
}
