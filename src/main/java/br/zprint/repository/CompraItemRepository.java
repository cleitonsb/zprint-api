package br.zprint.repository;

import br.zprint.model.CompraItem;
import br.zprint.model.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CompraItemRepository extends CrudRepository<CompraItem, Long> {
    @Modifying
    @Query("delete from CompraItem c where c.compra.id = ?1")
    void deletByCompra(Long idCompra);
}
