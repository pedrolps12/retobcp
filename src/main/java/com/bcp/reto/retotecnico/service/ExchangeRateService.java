package com.bcp.reto.retotecnico.service;

import com.bcp.reto.retotecnico.dto.RequestCurrency;
import com.bcp.reto.retotecnico.dto.ResponseCurrency;
import io.reactivex.rxjava3.core.Single;

public interface ExchangeRateService {


  Single<ResponseCurrency> processCurrency(RequestCurrency requestCurrency);


}
