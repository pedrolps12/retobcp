package com.bcp.reto.retotecnico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseCurrency {

  private Double amount;

  private Double currencyAmount;

  private String initialCurrency;

  private String destinationCurrency;

  private Double currency;

}
