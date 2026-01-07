package com.company.account_service.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementDto {

    @JsonProperty("Fecha")
    private LocalDateTime date;

    @JsonProperty("Cliente")
    private String clientName;

    @JsonProperty("Numero Cuenta")
    private String accountNumber;

    @JsonProperty("Tipo")
    private String accountType;

    @JsonProperty("Saldo Inicial")
    private BigDecimal initialBalance;

    @JsonProperty("Estado")
    private Boolean status;

    @JsonProperty("Movimiento")
    @NotNull(message = "El valor del movimiento es obligatorio")
    private BigDecimal movementValue;

    @JsonProperty("Saldo Disponible")
    private BigDecimal availableBalance;
}
