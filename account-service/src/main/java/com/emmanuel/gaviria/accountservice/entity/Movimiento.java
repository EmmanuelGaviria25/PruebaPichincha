package com.emmanuel.gaviria.accountservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    private String tipoMovimiento; // "Retiro" o "Deposito"
    private double valor;
    private double saldo;

    @ManyToOne
    private Cuenta cuenta;

}
