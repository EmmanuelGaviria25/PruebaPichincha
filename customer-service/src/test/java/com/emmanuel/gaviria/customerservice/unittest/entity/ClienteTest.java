package com.emmanuel.gaviria.customerservice.unittest.entity;

import com.emmanuel.gaviria.customerservice.entity.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {

    @Test
    public void testClienteProperties() {
        Cliente cliente = new Cliente();
        cliente.setClienteId("12345");
        cliente.setContraseña("password");
        cliente.setEstado("Activo");
        cliente.setNombre("John Doe");
        cliente.setGenero("Masculino");
        cliente.setEdad(30);
        cliente.setIdentificacion("123456789");
        cliente.setDireccion("123 Main St");
        cliente.setTelefono("555-1234");

        assertEquals("12345", cliente.getClienteId());
        assertEquals("password", cliente.getContraseña());
        assertEquals("Activo", cliente.getEstado());
        assertEquals("John Doe", cliente.getNombre());
        assertEquals("Masculino", cliente.getGenero());
        assertEquals(30, cliente.getEdad());
        assertEquals("123456789", cliente.getIdentificacion());
        assertEquals("123 Main St", cliente.getDireccion());
        assertEquals("555-1234", cliente.getTelefono());
    }
}
