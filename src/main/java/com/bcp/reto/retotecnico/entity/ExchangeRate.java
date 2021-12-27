package com.bcp.reto.retotecnico.entity;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("exchange_rate")
@Getter
@Setter
@Builder
public class ExchangeRate {

  @Id
  private UUID id;

  private String code;

  private String description;

  private Double amount;

}
