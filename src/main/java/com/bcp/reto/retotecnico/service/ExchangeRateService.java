package com.bcp.reto.retotecnico.service;

import com.bcp.reto.retotecnico.dto.RequestCurrency;
import com.bcp.reto.retotecnico.dto.RequestSaveExchangeCurrency;
import com.bcp.reto.retotecnico.dto.ResponseCurrency;
import com.bcp.reto.retotecnico.dto.ResponseSaveCurrency;
import io.reactivex.rxjava3.core.Single;

public interface ExchangeRateService {


  Single<ResponseSaveCurrency> saveCurrency(RequestSaveExchangeCurrency requestCurrency);

  Single<ResponseCurrency> processCurrency(RequestCurrency requestCurrency);

  Single<ResponseCurrency> processCurrencyWithZip(RequestCurrency requestCurrency);

}
