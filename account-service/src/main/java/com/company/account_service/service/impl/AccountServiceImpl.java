package com.company.account_service.service.impl;

import com.company.account_service.exception.InsufficientBalanceException;
import com.company.account_service.model.dto.AccountDto;
import com.company.account_service.model.dto.MovementDto;
import com.company.account_service.model.entity.Account;
import com.company.account_service.model.entity.Movement;
import com.company.account_service.repository.AccountRepository;
import com.company.account_service.repository.MovementRepository;
import com.company.account_service.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;

    @Override
    @Transactional
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = Account.builder()
                .accountNumber(accountDto.getAccountNumber())
                .accountType(accountDto.getAccountType())
                .balance(accountDto.getInitialBalance())
                .status(true)
                .clientName(accountDto.getClientName())
                .clientIdentification(accountDto.getClientIdentification())
                .build();

        Account savedAccount = accountRepository.save(account);
        return mapToAccountDto(savedAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto getAccountByNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada: " + accountNumber));
        return mapToAccountDto(account);
    }

    @Override
    @Transactional
    public MovementDto registerMovement(MovementDto movementDto) {
        Account account = accountRepository.findByAccountNumber(movementDto.getAccountNumber())
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada: " + movementDto.getAccountNumber()));

        BigDecimal amount = movementDto.getMovementValue();
        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance = currentBalance.add(amount);

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientBalanceException("Saldo no disponible");
        }

        account.setBalance(newBalance);
        accountRepository.save(account);

        Movement movement = Movement.builder()
                .date(LocalDateTime.now())
                .movementType(amount.compareTo(BigDecimal.ZERO) > 0 ? "DEPOSIT" : "WITHDRAWAL")
                .value(amount)
                .balance(newBalance)
                .account(account)
                .build();

        movementRepository.save(movement);

        return MovementDto.builder()
                .date(movement.getDate())
                .clientName(account.getClientName())
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .initialBalance(currentBalance)
                .status(account.getStatus())
                .movementValue(amount)
                .availableBalance(newBalance)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementDto> getAccountStatement(String accountNumber, LocalDateTime startDate, LocalDateTime endDate) {
        List<Movement> movements = movementRepository.findByAccountAccountNumberAndDateBetween(accountNumber, startDate, endDate);
        return mapToMovementDtoList(movements);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementDto> getClientStatement(String clientName, LocalDateTime startDate, LocalDateTime endDate) {
        List<Movement> movements = movementRepository.findByAccountClientNameAndDateBetween(clientName, startDate, endDate);
        return mapToMovementDtoList(movements);
    }

    @Override
    @Transactional
    public void deleteAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada"));
        accountRepository.delete(account);
    }

    private AccountDto mapToAccountDto(Account account) {
        return AccountDto.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .initialBalance(account.getBalance())
                .status(account.getStatus())
                .clientName(account.getClientName())
                .clientIdentification(account.getClientIdentification())
                .build();
    }

    private List<MovementDto> mapToMovementDtoList(List<Movement> movements) {
        return movements.stream().map(m -> MovementDto.builder()
                .date(m.getDate())
                .clientName(m.getAccount().getClientName())
                .accountNumber(m.getAccount().getAccountNumber())
                .accountType(m.getAccount().getAccountType())
                .initialBalance(m.getBalance().subtract(m.getValue()))
                .status(m.getAccount().getStatus())
                .movementValue(m.getValue())
                .availableBalance(m.getBalance())
                .build()
        ).collect(Collectors.toList());
    }
}
