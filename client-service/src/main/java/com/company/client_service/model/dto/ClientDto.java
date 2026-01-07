package com.company.client_service.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientDto {

    @JsonProperty("ClienteId")
    private String clientId;

    @JsonProperty("Contraseña")
    @NotEmpty(message = "La contraseña es obligatoria")
    private String password;

    @JsonProperty("Estado")
    @NotNull(message = "El estado es obligatorio")
    private Boolean status;

    // Campos heredados de Persona
    
    @JsonProperty("Nombres")
    @NotEmpty(message = "El nombre es obligatorio")
    private String name;

    @JsonProperty("Genero")
    private String gender;

    @JsonProperty("Edad")
    private Integer age;

    @JsonProperty("Identificacion")
    @NotEmpty(message = "La identificación es obligatoria")
    private String identification;

    @JsonProperty("Dirección")
    private String address;

    @JsonProperty("Teléfono")
    private String phone;
}
