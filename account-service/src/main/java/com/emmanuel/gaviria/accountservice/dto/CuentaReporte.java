package com.emmanuel.gaviria.accountservice.dto;

import com.emmanuel.gaviria.accountservice.entity.Cuenta;
import com.emmanuel.gaviria.accountservice.entity.Movimiento;

import java.util.List;

public class CuentaReporte {
    private Cuenta cuenta;
    private List<Movimiento> movimientos;

    public CuentaReporte(Cuenta cuenta, List<Movimiento> movimientos) {
        this.cuenta = cuenta;
        this.movimientos = movimientos;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}