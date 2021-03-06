package br.zprint.repository;

import br.zprint.model.Pagamento;
import br.zprint.model.VendaItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VendaItemRepository extends CrudRepository<VendaItem, Long> {

    @Modifying
    @Query("update VendaItem v set v.situacao = false where v.venda.id = ?1")
    void deletByVenda(Long idVenda);
}
