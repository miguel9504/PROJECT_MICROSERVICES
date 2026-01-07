package com.company.client_service.service;

import com.company.client_service.model.dto.ClientDto;
import java.util.List;

public interface ClientService {
    ClientDto createClient(ClientDto clientDto);
    ClientDto getClientByClientId(String clientId);
    List<ClientDto> getAllClients();
    ClientDto updateClient(String clientId, ClientDto clientDto);
    void deleteClient(String clientId);
}
