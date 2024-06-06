package com.emmanuel.gaviria.accountservice.controller;

import com.emmanuel.gaviria.accountservice.entity.Cuenta;
import com.emmanuel.gaviria.accountservice.service.CuentaService;
import com.emmanuel.gaviria.accountservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.getCuentaById(id).orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));
        return ResponseEntity.ok(cuenta);
    }

    @PostMapping("/cliente/{clienteId}")
    public Cuenta createCuenta(@PathVariable Long clienteId, @RequestBody Cuenta cuenta) {
        return cuentaService.createCuenta(cuenta, clienteId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaDetails) {
        Cuenta updatedCuenta = cuentaService.updateCuenta(id, cuentaDetails);
        return ResponseEntity.ok(updatedCuenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }
}