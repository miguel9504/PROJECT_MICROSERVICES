package com.company.client_service.controller;

import com.company.client_service.model.dto.ClientDto;
import com.company.client_service.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes") // Endpoint en Español según requisito F1
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto) {
        return new ResponseEntity<>(clientService.createClient(clientDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable String clientId) {
        return ResponseEntity.ok(clientService.getClientByClientId(clientId));
    }

    @PutMapping("/{clientId}") // O Patch según preferencia
    public ResponseEntity<ClientDto> updateClient(@PathVariable String clientId, @Valid @RequestBody ClientDto clientDto) {
        return ResponseEntity.ok(clientService.updateClient(clientId, clientDto));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable String clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }
}
