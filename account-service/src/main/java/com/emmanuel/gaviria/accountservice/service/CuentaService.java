package com.emmanuel.gaviria.accountservice.service;

import com.emmanuel.gaviria.accountservice.client.CustomerClient;
import com.emmanuel.gaviria.accountservice.dto.Cliente;
import com.emmanuel.gaviria.accountservice.entity.Cuenta;
import com.emmanuel.gaviria.accountservice.repository.CuentaRepository;
import com.emmanuel.gaviria.accountservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private CustomerClient customerClient;

    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    public Optional<Cuenta> getCuentaById(Long id) {
        return cuentaRepository.findById(id);
    }

    public Cuenta createCuenta(Cuenta cuenta, Long clienteId) {
        // Obtener cliente desde el customer-service
        Cliente cliente = customerClient.getClienteById(clienteId);
        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }
        cuenta.setClienteId(clienteId);
        cuenta.setSaldoDisponible(cuenta.getSaldoInicial()); // Inicializar saldo disponible
        return cuentaRepository.save(cuenta);
    }

    public Cuenta updateCuenta(Long id, Cuenta cuentaDetails) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));
        cuenta.setNumeroCuenta(cuentaDetails.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDetails.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDetails.getSaldoInicial());
        cuenta.setSaldoDisponible(cuentaDetails.getSaldoDisponible());
        cuenta.setEstado(cuentaDetails.getEstado());
        cuenta.setClienteId(cuentaDetails.getClienteId());
        return cuentaRepository.save(cuenta);
    }

    public void deleteCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));
        cuentaRepository.delete(cuenta);
    }

}
