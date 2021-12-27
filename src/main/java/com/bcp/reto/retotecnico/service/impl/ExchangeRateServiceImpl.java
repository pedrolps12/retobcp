package com.bcp.reto.retotecnico.service.impl;

import com.bcp.reto.retotecnico.dto.RequestCurrency;
import com.bcp.reto.retotecnico.dto.RequestSaveExchangeCurrency;
import com.bcp.reto.retotecnico.dto.ResponseCurrency;
import com.bcp.reto.retotecnico.dto.ResponseSaveCurrency;
import com.bcp.reto.retotecnico.entity.ExchangeRate;
import com.bcp.reto.retotecnico.repository.ExchangeCurrencyRepository;
import com.bcp.reto.retotecnico.service.ExchangeRateService;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.stereotype.Service;


/**
 * The type Exchange rate service.
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

  private final ExchangeCurrencyRepository repository;

  /**
   * Instantiates a new Exchange rate service.
   *
   * @param repository the repository
   */
  public ExchangeRateServiceImpl(ExchangeCurrencyRepository repository) {
    this.repository = repository;
  }

  @Override
  public Single<ResponseSaveCurrency> saveCurrency(RequestSaveExchangeCurrency requestCurrency) {
    return Single.just(requestCurrency).map(
        request -> ExchangeRate.builder().code(request.getCode())
            .description(request.getDescription()).amount(request.getAmount()).build())
        .flatMap(repository::save)
        .map(exchangeCurrency -> ResponseSaveCurrency.builder().code(exchangeCurrency.getCode())
            .descripcion(exchangeCurrency.getDescription()).amount(
                exchangeCurrency.getAmount()).id(exchangeCurrency.getId().toString()).build());
  }

  @Override
  public Single<ResponseCurrency> processCurrency(RequestCurrency requestCurrency) {
    return repository.findByCode(requestCurrency.getInitialCurrency())
        .map(exchangeCurrency -> ResponseCurrency.builder()
            .currencyAmount(requestCurrency.getAmount() * exchangeCurrency.getAmount())
            .currency(exchangeCurrency.getAmount())
            .amount(requestCurrency.getAmount())
            .initialCurrency(requestCurrency.getInitialCurrency())
            .build())
        .flatMap(responseCurrency -> repository.findByCode(requestCurrency.getDestinationCurrency())
            .map(exchangeCurrency -> {
              responseCurrency.setDestinationCurrency(requestCurrency.getDestinationCurrency());
              responseCurrency
                  .setCurrencyAmount(responseCurrency.getCurrencyAmount() / exchangeCurrency
                      .getAmount());
              return responseCurrency;
            })
        ).toSingle();

  }

  @Override
  public Single<ResponseCurrency> processCurrencyWithZip(RequestCurrency requestCurrency) {
    Single<ExchangeRate> initialCurrency = repository
        .findByCode(requestCurrency.getInitialCurrency())
        .switchIfEmpty(Maybe.error(new Exception("Initial currency not found"))).toSingle();
    Single<ExchangeRate> destinationCurrency = repository
        .findByCode(requestCurrency.getDestinationCurrency())
        .switchIfEmpty(Maybe.error(new Exception("Destination currency not found"))).toSingle();
    return Single.zip(initialCurrency, destinationCurrency,
        (s1, s2) -> builderResponse(s1, s2, requestCurrency));
  }

  private ResponseCurrency builderResponse(ExchangeRate exchangeCurrency,
      ExchangeRate exchangeCurrency2, RequestCurrency requestCurrency) {
    return ResponseCurrency
        .builder()
        .amount(requestCurrency.getAmount())
        .currencyAmount(
            requestCurrency.getAmount() * exchangeCurrency.getAmount() / exchangeCurrency2
                .getAmount())
        .initialCurrency(requestCurrency.getInitialCurrency())
        .destinationCurrency(requestCurrency.getDestinationCurrency())
        .currency(exchangeCurrency.getAmount())
        .build();
  }

}
