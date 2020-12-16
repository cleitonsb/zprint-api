package br.zprint.repository;

import br.zprint.model.Pagamento;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ServicoItemRepository extends CrudRepository<Pagamento, Long> {

    @Modifying
    @Query("update ServicoItem s set s.situacao = false where s.servico.id = ?1")
    void deletByServico(Long idServico);
}
