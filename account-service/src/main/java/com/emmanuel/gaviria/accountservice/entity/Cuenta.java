package com.emmanuel.gaviria.accountservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Double saldoDisponible;
    private String estado;
    private Long clienteId;
}
