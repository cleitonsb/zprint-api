package br.zprint.repository;

import br.zprint.model.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
}
