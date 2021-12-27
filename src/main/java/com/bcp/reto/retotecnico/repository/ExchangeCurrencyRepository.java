package com.bcp.reto.retotecnico.repository;

import com.bcp.reto.retotecnico.entity.ExchangeRate;
import io.reactivex.rxjava3.core.Maybe;
import java.util.UUID;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface ExchangeCurrencyRepository extends
    RxJava3CrudRepository<ExchangeRate, UUID> {

  Maybe<ExchangeRate> findByCode(String code);

}
