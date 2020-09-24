package br.zprint.repository;

import br.zprint.model.Cidade;
import br.zprint.model.Estado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CidadeRepository extends CrudRepository<Cidade, Long> {

    @Query("select c from Cidade c where c.estado = ?1")
    List<Cidade> findByEstado(Estado estado);
}
