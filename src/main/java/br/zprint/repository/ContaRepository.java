package br.zprint.repository;

import br.zprint.model.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ContaRepository extends CrudRepository<Conta, Long> {

    @Query("select c from Conta c where (c.valor = ?1 or c.data = ?1) and c.situacao = true")
    Iterable<Conta> findByParam(String param);

    @Query("select c from Conta c where (c.valor = ?1 or c.data = ?1) and c.situacao = true")
    Page<Conta> findByParam(String param, Pageable page);

    @Query("select c from Conta c where c.situacao = true order by c.id desc")
    Page<Conta> findAll(Pageable page);

    @Query(value = "select vc.venda_id, v.nome, c.valor, c.troco, sc.servico_id, p.nome as nomePessoa\n" +
            "from contas c\n" +
            "         left join vendas_contas vc on c.id = vc.conta_id\n" +
            "         left join vendas v on vc.venda_id = v.id\n" +
            "         left join servicos_contas sc on c.id = sc.conta_id\n" +
            "         left join servicos s on sc.servico_id = s.id\n" +
            "         left join pessoas p on s.pessoa_id = p.id\n" +
            "\n" +
            "where c.caixa_id is null\n" +
            "  and c.situacao = true and (vc.venda_id is not null or sc.servico_id is not null)", nativeQuery = true)
    List findOpen();

    @Query("select c from Conta c where c.caixa.id = ?1 and c.situacao = true")
    Iterable<Conta> findByCaixa(Long idCaixa);
}
