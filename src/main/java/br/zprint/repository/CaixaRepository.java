package br.zprint.repository;

import br.zprint.model.Caixa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface CaixaRepository extends CrudRepository<Caixa, Long> {
    @Query("select c from Caixa c where c.dataAbertura = ?1 or c.situacao = true")
    Iterable<Caixa> findByParam(LocalDateTime param);

    @Query("select c, (sum(p.valor) - c.quebra + c.fundo) as total from Caixa c " +
            "left join Conta cp on c.id = cp.caixa.id " +
            "left join Pagamento p on cp.id = p.conta.id " +
            "where c.situacao = true and cp.situacao = true and p.situacao = true " +
            "group by c.id order by c.id desc")
    Page<Caixa> findByParam(String param, Pageable page);

    @Query("select c, (sum(p.valor) - c.quebra + c.fundo) as total from Caixa c " +
            "left join Conta cp on c.id = cp.caixa.id and cp.situacao = true " +
            "left join Pagamento p on cp.id = p.conta.id and p.situacao = true " +
            "where c.situacao = true " +
            "group by c.id order by c.id desc")
    Page<Caixa> findAll(Pageable page);

    @Query("select c from Caixa c where c.situacao = true and c.dataFechamento is null order by c.id desc")
    Caixa getOpen();

    @Query(value = "select vc.venda_id, v.data, v.nome, v.total, c.valor, c.troco, u.nome as nomeusuario from contas c\n" +
            "inner join vendas_contas vc on c.id = vc.conta_id\n" +
            "inner join vendas v on vc.venda_id = v.id\n" +
            "inner join usuarios u on u.id = v.usuario_id\n" +
            "where c.caixa_id = ?1 and c.situacao = true", nativeQuery = true)
    List findByCaixa(Long idCaixa);

    @Query("select c, (sum(p.valor)) as total from Caixa c " +
        "left join Conta cp on c.id = cp.caixa.id and cp.situacao = true " +
        "left join Pagamento p on cp.id = p.conta.id and p.situacao = true " +
        "where c.situacao = true and c.dataFechamento >= ?1 and c.dataFechamento <= ?2 " +
        "group by c")
    List getByDay(Date dataini, Date datafim);
}

//@Query("select c, (sum(p.valor) - c.quebra + c.fundo) as total from Caixa c " +
//        "left join Conta cp on c.id = cp.caixa.id and cp.situacao = true " +
//        "left join Pagamento p on cp.id = p.conta.id and p.situacao = true " +
//        "where c.situacao = true and c.dataFechamento >= ?1 and c.dataFechamento <= ?2 " +
//        "group by c.dataFechamento order by c.id desc")
