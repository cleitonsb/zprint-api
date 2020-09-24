package br.zprint.repository;

import br.zprint.model.Pagamento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PagamentoRepository extends CrudRepository<Pagamento, Long> {
    @Query("select p from Pagamento p where p.id = ?1 and p.situacao = true")
    Iterable<Pagamento> findByVenda(Number idVenda);

    @Query("select p.tipoPagamento, sum(p.valor) as total from Pagamento p " +
            "inner join Venda v on v = p.venda " +
            "where v.caixa.id = ?1 and p.situacao = true and v.situacao = true " +
            "group by p.tipoPagamento")
    List findByCaixa(Long idCaixa);

}
