package com.company.client_service.mapper;

import com.company.client_service.model.dto.ClientDto;
import com.company.client_service.model.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientDto dto) {
        if (dto == null) return null;

        Client client = new Client();
        // Datos de Persona
        client.setName(dto.getName());
        client.setGender(dto.getGender());
        client.setAge(dto.getAge());
        client.setIdentification(dto.getIdentification());
        client.setAddress(dto.getAddress());
        client.setPhone(dto.getPhone());
        
        // Datos de Cliente
        client.setClientId(dto.getClientId());
        client.setPassword(dto.getPassword());
        client.setStatus(dto.getStatus());

        return client;
    }

    public ClientDto toDto(Client client) {
        if (client == null) return null;

        ClientDto dto = new ClientDto();
        // Datos de Persona
        dto.setName(client.getName());
        dto.setGender(client.getGender());
        dto.setAge(client.getAge());
        dto.setIdentification(client.getIdentification());
        dto.setAddress(client.getAddress());
        dto.setPhone(client.getPhone());

        // Datos de Cliente
        dto.setClientId(client.getClientId());
        dto.setPassword(client.getPassword());
        dto.setStatus(client.getStatus());

        return dto;
    }
    
    public void updateEntityFromDto(ClientDto dto, Client entity) {
        if (dto == null || entity == null) return;
        
        // Actualizamos solo los campos que vienen en el DTO (o todos, según lógica PUT)
        entity.setName(dto.getName());
        entity.setGender(dto.getGender());
        entity.setAge(dto.getAge());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        // Nota: identification y clientId usualmente no se actualizan, pero depende del negocio.
    }
}
