package com.kmv.goforrest.repository;

import com.kmv.goforrest.model.Pagamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends CrudRepository<Pagamento, Long> {
    Iterable<Pagamento> getAllByStatusIsTrue();
    Iterable<Pagamento> getAllByStatusIsFalse();

    Iterable<Pagamento> getAllByTipoPagamentoIsLike(String tipo);

}
