package com.company.client_service.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "id")
public class Client extends Person {

    @Column(name = "client_id", unique = true, nullable = false, length = 50)
    private String clientId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean status;
}
