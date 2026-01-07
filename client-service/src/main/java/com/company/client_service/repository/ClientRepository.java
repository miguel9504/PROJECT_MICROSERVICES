package com.company.client_service.repository;

import com.company.client_service.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
    // Método para buscar por el ID de negocio (clientId)
    Optional<Client> findByClientId(String clientId);
    
    // Método para buscar por identificación (DNI)
    Optional<Client> findByIdentification(String identification);
    
    // Para validaciones de existencia
    boolean existsByClientId(String clientId);
    boolean existsByIdentification(String identification);
}
