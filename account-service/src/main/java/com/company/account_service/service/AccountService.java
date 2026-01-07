package com.company.account_service.service;

import com.company.account_service.model.dto.AccountDto;
import com.company.account_service.model.dto.MovementDto;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountService {
    
    AccountDto createAccount(AccountDto accountDto);
    
    AccountDto getAccountByNumber(String accountNumber);
    
    MovementDto registerMovement(MovementDto movementDto);
    
    List<MovementDto> getAccountStatement(String accountNumber, LocalDateTime startDate, LocalDateTime endDate);
    
    // Nuevo método para reporte por cliente
    List<MovementDto> getClientStatement(String clientName, LocalDateTime startDate, LocalDateTime endDate);
    
    void deleteAccount(String accountNumber);
}
