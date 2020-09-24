package br.zprint.repository;

import br.zprint.model.Usuario;
import br.zprint.model.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("select v from Venda v where v.nome like %?1% and v.situacao = true")
    Iterable<Venda> findByParam(String param);

    @Query("select v from Venda v where v.nome like %?1% and v.situacao = true order by v.id desc")
    Page<Venda> findByParam(String param, Pageable page);

    @Query("select v from Venda v where v.situacao = true order by v.id desc")
    Page<Venda> findAll(Pageable page);

    @Query("select v from Venda v where v.caixa is null and v.situacao = true")
    Iterable<Venda> findOpen();

    @Query("select v from Venda v where v.caixa.id = ?1 and v.situacao = true")
    Iterable<Venda> findByCaixa(Long idCaixa);
}
