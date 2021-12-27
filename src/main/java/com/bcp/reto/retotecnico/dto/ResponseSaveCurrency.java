package com.bcp.reto.retotecnico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseSaveCurrency {

  private String id;

  private String code;

  private String descripcion;

  private Double amount;

}
