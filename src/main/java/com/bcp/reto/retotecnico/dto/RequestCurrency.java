package com.bcp.reto.retotecnico.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The type Request currency.
 */
@Getter
@Setter
@NoArgsConstructor
public class RequestCurrency {

  @NotNull(message = "Amount is mandatory")
  private Double amount;

  @NotNull(message = "Initial currency is mandatory")
  private String initialCurrency;

  @NotNull(message = "Destination currency is mandatory")
  private String destinationCurrency;

}
