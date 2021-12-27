package com.bcp.reto.retotecnico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * The type Response save currency.
 */
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
