package br.zprint.repository;

import br.zprint.model.PlanoConta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PlanoContaRepository extends CrudRepository<PlanoConta, Long> {

    @Query("select c from PlanoConta c where  c.situacao = true")
    Iterable<PlanoConta> findByParam(String param);

    @Query("select c from PlanoConta c where c.situacao = true")
    Page<PlanoConta> findByParam(String param, Pageable page);

    @Query("select c from PlanoConta c where c.situacao = true")
    Page<PlanoConta> findAll(Pageable page);
}
