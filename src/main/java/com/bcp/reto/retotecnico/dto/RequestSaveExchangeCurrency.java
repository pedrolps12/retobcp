package com.bcp.reto.retotecnico.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestSaveExchangeCurrency {

  @NotNull
  private String code;

  @NotNull
  private String description;

  @NotNull
  private Double amount;

}
