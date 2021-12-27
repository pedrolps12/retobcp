package com.bcp.reto.retotecnico.service;

import com.bcp.reto.retotecnico.dto.RequestCurrency;
import com.bcp.reto.retotecnico.dto.RequestSaveExchangeCurrency;
import com.bcp.reto.retotecnico.dto.ResponseCurrency;
import com.bcp.reto.retotecnico.dto.ResponseSaveCurrency;
import io.reactivex.rxjava3.core.Single;


/**
 * The interface Exchange rate service.
 */
public interface ExchangeRateService {


  /**
   * Save currency single.
   *
   * @param requestCurrency the request currency
   * @return the single
   */
  Single<ResponseSaveCurrency> saveCurrency(RequestSaveExchangeCurrency requestCurrency);

  /**
   * Process currency single.
   *
   * @param requestCurrency the request currency
   * @return the single
   */
  Single<ResponseCurrency> processCurrency(RequestCurrency requestCurrency);

  /**
   * Process currency with zip single.
   *
   * @param requestCurrency the request currency
   * @return the single
   */
  Single<ResponseCurrency> processCurrencyWithZip(RequestCurrency requestCurrency);

}
