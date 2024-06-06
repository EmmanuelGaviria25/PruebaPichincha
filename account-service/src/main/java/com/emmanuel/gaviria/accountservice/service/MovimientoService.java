package com.emmanuel.gaviria.accountservice.service;

import com.emmanuel.gaviria.accountservice.entity.Cuenta;
import com.emmanuel.gaviria.accountservice.entity.Movimiento;
import com.emmanuel.gaviria.accountservice.exception.InsufficientFundsException;
import com.emmanuel.gaviria.accountservice.exception.ResourceNotFoundException;
import com.emmanuel.gaviria.accountservice.repository.CuentaRepository;
import com.emmanuel.gaviria.accountservice.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    private static final String DEPOSITO = "Deposito";

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Optional<Movimiento> getMovimientoById(Long id) {
        return movimientoRepository.findById(id);
    }

    public List<Movimiento> getMovimientosByCuentaId(Long cuentaId) {
        return movimientoRepository.findByCuentaId(cuentaId);
    }

    public Movimiento registrarMovimiento(Long cuentaId, Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));
        double nuevoSaldo = cuenta.getSaldoDisponible() + (movimiento.getTipoMovimiento().equalsIgnoreCase(DEPOSITO) ? movimiento.getValor() : -movimiento.getValor());

        if (nuevoSaldo < 0) {
            throw new RuntimeException("Saldo no disponible");
        }

        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);
        cuenta.setSaldoDisponible(nuevoSaldo);
        cuentaRepository.save(cuenta);
        return movimientoRepository.save(movimiento);
    }

    public Movimiento updateMovimiento(Long id, Movimiento movimientoDetails) {
        Movimiento movimiento = movimientoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado"));
        movimiento.setFecha(movimientoDetails.getFecha());
        movimiento.setTipoMovimiento(movimientoDetails.getTipoMovimiento());
        movimiento.setValor(movimientoDetails.getValor());
        movimiento.setSaldo(movimientoDetails.getSaldo());
        return movimientoRepository.save(movimiento);
    }

    public void deleteMovimiento(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado"));
        movimientoRepository.delete(movimiento);
    }
}
