package com.bcp.reto.retotecnico.repository;

import com.bcp.reto.retotecnico.entity.ExchangeRate;
import io.reactivex.rxjava3.core.Maybe;
import java.util.UUID;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;


/**
 * The interface Exchange currency repository.
 */
public interface ExchangeCurrencyRepository extends
    RxJava3CrudRepository<ExchangeRate, UUID> {

  /**
   * Find by code maybe.
   *
   * @param code the code
   * @return the maybe
   */
  Maybe<ExchangeRate> findByCode(String code);

}
