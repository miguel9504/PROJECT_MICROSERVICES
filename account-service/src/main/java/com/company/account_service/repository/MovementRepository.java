package com.company.account_service.repository;

import com.company.account_service.model.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findByAccountAccountNumberAndDateBetween(String accountNumber, LocalDateTime startDate, LocalDateTime endDate);
    
    // Nuevo método para el reporte por cliente
    List<Movement> findByAccountClientNameAndDateBetween(String clientName, LocalDateTime startDate, LocalDateTime endDate);
}
