package com.company.client_service.service.impl;

import com.company.client_service.exception.ResourceNotFoundException;
import com.company.client_service.mapper.ClientMapper;
import com.company.client_service.model.dto.ClientDto;
import com.company.client_service.model.entity.Client;
import com.company.client_service.repository.ClientRepository;
import com.company.client_service.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientDto createClient(ClientDto clientDto) {
        // Aquí podrías validar si ya existe el clientId o identification
        if (clientRepository.existsByClientId(clientDto.getClientId())) {
            throw new RuntimeException("El cliente con ID " + clientDto.getClientId() + " ya existe.");
        }
        
        Client client = clientMapper.toEntity(clientDto);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDto getClientByClientId(String clientId) {
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + clientId));
        return clientMapper.toDto(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClientDto updateClient(String clientId, ClientDto clientDto) {
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + clientId));
        
        clientMapper.updateEntityFromDto(clientDto, client);
        Client updatedClient = clientRepository.save(client);
        return clientMapper.toDto(updatedClient);
    }

    @Override
    @Transactional
    public void deleteClient(String clientId) {
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + clientId));
        clientRepository.delete(client);
    }
}
