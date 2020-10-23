package br.zprint.repository;

import br.zprint.model.Caixa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
@Transactional
public interface CaixaRepository extends CrudRepository<Caixa, Long> {
    @Query("select c from Caixa c where c.dataAbertura = ?1 or c.situacao = true")
    Iterable<Caixa> findByParam(LocalDateTime param);

    @Query("select c, sum(p.valor) as total from Caixa c " +
            "left join Conta cp on c.id = cp.caixa.id " +
            "left join Pagamento p on cp.id = p.conta.id " +
            "where c.situacao = true and cp.situacao = true and p.situacao = true " +
            "group by c.id order by c.id desc")
    Page<Caixa> findByParam(String param, Pageable page);

    @Query("select c, sum(p.valor) as total from Caixa c " +
            "left join Conta cp on c.id = cp.caixa.id and cp.situacao = true " +
            "left join Pagamento p on cp.id = p.conta.id and p.situacao = true " +
            "where c.situacao = true " +
            "group by c.id order by c.id desc")
    Page<Caixa> findAll(Pageable page);

    @Query("select c from Caixa c where c.situacao = true and c.dataFechamento is null order by c.id desc")
    Caixa getOpen();
}
