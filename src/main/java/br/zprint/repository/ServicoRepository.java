package br.zprint.repository;

import br.zprint.model.Servico;
import br.zprint.model.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query("select s from Servico s where s.usuario.nome like %?1% and s.situacao = true")
    Iterable<Servico> findByParam(String param);

    @Query("select s from Servico s where s.usuario.nome like %?1% and s.situacao = true order by s.id desc")
    Page<Servico> findByParam(String param, Pageable page);

    @Query("select s from Servico s where s.situacao = true order by s.id desc")
    Page<Servico> findAll(Pageable page);
}
