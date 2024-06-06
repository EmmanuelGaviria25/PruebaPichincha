package com.emmanuel.gaviria.accountservice.controller;

import com.emmanuel.gaviria.accountservice.entity.Movimiento;
import com.emmanuel.gaviria.accountservice.service.MovimientoService;
import com.emmanuel.gaviria.accountservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public List<Movimiento> getAllMovimientos() {
        return movimientoService.getAllMovimientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.getMovimientoById(id).orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado"));
        return ResponseEntity.ok(movimiento);
    }

    @GetMapping("/cuenta/{cuentaId}")
    public List<Movimiento> getMovimientosByCuentaId(@PathVariable Long cuentaId) {
        return movimientoService.getMovimientosByCuentaId(cuentaId);
    }

    @PostMapping("/cuenta/{cuentaId}")
    public ResponseEntity<Movimiento> registrarMovimiento(@PathVariable Long cuentaId, @RequestBody Movimiento movimiento) {
        Movimiento nuevoMovimiento = movimientoService.registrarMovimiento(cuentaId, movimiento);
        return ResponseEntity.ok(nuevoMovimiento);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimientoDetails) {
        Movimiento updatedMovimiento = movimientoService.updateMovimiento(id, movimientoDetails);
        return ResponseEntity.ok(updatedMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}
