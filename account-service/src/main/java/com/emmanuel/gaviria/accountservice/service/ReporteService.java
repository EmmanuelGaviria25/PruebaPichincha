package com.emmanuel.gaviria.accountservice.service;


import com.emmanuel.gaviria.accountservice.dto.CuentaReporte;
import com.emmanuel.gaviria.accountservice.dto.ReporteEstadoCuenta;
import com.emmanuel.gaviria.accountservice.entity.Cuenta;
import com.emmanuel.gaviria.accountservice.entity.Movimiento;
import com.emmanuel.gaviria.accountservice.exception.ResourceNotFoundException;
import com.emmanuel.gaviria.accountservice.repository.CuentaRepository;
import com.emmanuel.gaviria.accountservice.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    public ReporteEstadoCuenta generarReporte(Long clienteId, Date fechaInicio, Date fechaFin) {
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        if (cuentas.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron cuentas para el cliente con ID: " + clienteId);
        }

        List<CuentaReporte> cuentasReporte = cuentas.stream()
                .map(cuenta -> {
                    List<Movimiento> movimientos = movimientoRepository.findByCuentaIdAndFechaBetween(cuenta.getId(), fechaInicio, fechaFin);
                    return new CuentaReporte(cuenta, movimientos);
                })
                .collect(Collectors.toList());

        return new ReporteEstadoCuenta(cuentasReporte);
    }
}