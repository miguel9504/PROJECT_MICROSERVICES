package com.company.account_service.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {

    @JsonProperty("Numero Cuenta")
    @NotEmpty(message = "El numero de cuenta es obligatorio")
    private String accountNumber;

    @JsonProperty("Tipo")
    @NotEmpty(message = "El tipo de cuenta es obligatorio")
    private String accountType;

    @JsonProperty("Saldo Inicial")
    @NotNull(message = "El saldo inicial es obligatorio")
    private BigDecimal initialBalance;

    @JsonProperty("Estado")
    private Boolean status;

    @JsonProperty("Cliente")
    @NotEmpty(message = "El nombre del cliente es obligatorio")
    private String clientName;
    
    // Campo auxiliar para identificar al cliente (podría venir en el header o body)
    @JsonProperty("Identificacion Cliente")
    private String clientIdentification;
}
