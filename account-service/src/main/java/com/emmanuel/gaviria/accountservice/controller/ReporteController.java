package com.emmanuel.gaviria.accountservice.controller;


import com.emmanuel.gaviria.accountservice.dto.ReporteEstadoCuenta;
import com.emmanuel.gaviria.accountservice.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {
    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<ReporteEstadoCuenta> generarReporte(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        ReporteEstadoCuenta reporte = reporteService.generarReporte(clienteId, fechaInicio, fechaFin);
        return ResponseEntity.ok(reporte);
    }
}