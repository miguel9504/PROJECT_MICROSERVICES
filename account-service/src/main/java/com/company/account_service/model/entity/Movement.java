package com.company.account_service.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(name = "movement_type", nullable = false, length = 50)
    private String movementType; // DEPOSIT, WITHDRAWAL

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal value;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance; // Balance after transaction

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
