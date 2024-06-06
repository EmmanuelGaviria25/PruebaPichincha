package com.emmanuel.gaviria.accountservice.dto;

import java.util.List;

public class ReporteEstadoCuenta {
    private List<CuentaReporte> cuentas;

    public ReporteEstadoCuenta(List<CuentaReporte> cuentas) {
        this.cuentas = cuentas;
    }

    public List<CuentaReporte> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaReporte> cuentas) {
        this.cuentas = cuentas;
    }
}