package br.zprint.repository;

import br.zprint.model.Equipamento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EquipamentoRepository extends CrudRepository<Equipamento, Long> {
    @Query("select e from Equipamento e where e.pessoa.id = ?1 and e.situacao = true")
    List findByPessoa(Long idPessoa);
}
