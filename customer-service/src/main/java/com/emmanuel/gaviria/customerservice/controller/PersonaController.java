package com.emmanuel.gaviria.customerservice.controller;

import com.emmanuel.gaviria.customerservice.entity.Persona;
import com.emmanuel.gaviria.customerservice.service.PersonaService;
import com.emmanuel.gaviria.customerservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.getAllPersonas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        Persona persona = personaService.getPersonaById(id).orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada"));
        return ResponseEntity.ok(persona);
    }

    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaService.createPersona(persona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails) {
        Persona updatedPersona = personaService.updatePersona(id, personaDetails);
        return ResponseEntity.ok(updatedPersona);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }
}
