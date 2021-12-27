package com.bcp.reto.retotecnico.expose;

import com.bcp.reto.retotecnico.dto.RequestCurrency;
import com.bcp.reto.retotecnico.dto.ResponseCurrency;
import com.bcp.reto.retotecnico.service.ExchangeRateService;
import io.reactivex.rxjava3.core.Single;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/retotecnico")
public class ExchangeRateController {

  private final ExchangeRateService service;

  public ExchangeRateController(ExchangeRateService service) {
    this.service = service;
  }

  @PostMapping("getCurrency")
  @ResponseBody
  public Single<ResponseCurrency> getCurrency(
      @Valid @RequestBody RequestCurrency requestCurrency) {

    return service.processCurrency(requestCurrency);
  }

}
