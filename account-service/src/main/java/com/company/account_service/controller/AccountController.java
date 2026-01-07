package com.company.account_service.controller;

import com.company.account_service.model.dto.AccountDto;
import com.company.account_service.model.dto.MovementDto;
import com.company.account_service.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    // --- CUENTAS ---

    @PostMapping("/cuentas")
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/cuentas/{accountNumber}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getAccountByNumber(accountNumber));
    }

    @DeleteMapping("/cuentas/{accountNumber}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountNumber) {
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.noContent().build();
    }

    // --- MOVIMIENTOS ---

    @PostMapping("/movimientos")
    public ResponseEntity<MovementDto> registerMovement(@Valid @RequestBody MovementDto movementDto) {
        return new ResponseEntity<>(accountService.registerMovement(movementDto), HttpStatus.CREATED);
    }

    @GetMapping("/movimientos/reportes")
    public ResponseEntity<List<MovementDto>> getReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam String cliente) {
        
        // Convertir LocalDate a LocalDateTime para cubrir todo el día
        LocalDateTime start = fechaInicio.atStartOfDay();
        LocalDateTime end = fechaFin.atTime(23, 59, 59);

        return ResponseEntity.ok(accountService.getClientStatement(cliente, start, end));
    }
}
