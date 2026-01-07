package com.company.client_service.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identification", unique = true, nullable = false, length = 20)
    private String identification;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 20)
    private String gender;

    private Integer age;

    @Column(length = 200)
    private String address;

    @Column(length = 20)
    private String phone;
}
