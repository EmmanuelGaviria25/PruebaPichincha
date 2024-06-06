package com.emmanuel.gaviria.customerservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cliente extends Persona {
    private String clienteId;
    private String contraseña;
    private String estado;
}
