package com.bcp.reto.retotecnico.service.impl;

import com.bcp.reto.retotecnico.dto.RequestCurrency;
import com.bcp.reto.retotecnico.dto.ResponseCurrency;
import com.bcp.reto.retotecnico.repository.ExchangeCurrencyRepository;
import com.bcp.reto.retotecnico.service.ExchangeRateService;
import io.reactivex.rxjava3.core.Single;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

  private final ExchangeCurrencyRepository repository;

  public ExchangeRateServiceImpl(ExchangeCurrencyRepository repository) {
    this.repository = repository;
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

}
