package com.emmanuel.gaviria.customerservice.unittest.service;

import com.emmanuel.gaviria.customerservice.entity.Cliente;
import com.emmanuel.gaviria.customerservice.repository.ClienteRepository;
import com.emmanuel.gaviria.customerservice.exception.ResourceNotFoundException;
import com.emmanuel.gaviria.customerservice.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    public void testGetClienteById() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setClienteId("12345");
        cliente.setContraseña("password");
        cliente.setEstado("Activo");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente found = clienteService.getClienteById(1L).orElse(null);

        assertNotNull(found);
        assertEquals("12345", found.getClienteId());
        assertEquals("password", found.getContraseña());
        assertEquals("Activo", found.getEstado());
    }

    @Test
    public void testCreateCliente() {
        Cliente cliente = new Cliente();
        cliente.setClienteId("12345");
        cliente.setContraseña("password");
        cliente.setEstado("Activo");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente created = clienteService.createCliente(cliente);

        assertNotNull(created);
        assertEquals("12345", created.getClienteId());
        assertEquals("password", created.getContraseña());
        assertEquals("Activo", created.getEstado());
    }

    @Test
    public void testUpdateCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setClienteId("12345");
        cliente.setContraseña("password");
        cliente.setEstado("Activo");

        Cliente updatedDetails = new Cliente();
        updatedDetails.setClienteId("54321");
        updatedDetails.setContraseña("newpassword");
        updatedDetails.setEstado("Inactivo");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente updated = clienteService.updateCliente(1L, updatedDetails);

        assertNotNull(updated);
        assertEquals("54321", updated.getClienteId());
        assertEquals("newpassword", updated.getContraseña());
        assertEquals("Inactivo", updated.getEstado());
    }

    @Test
    public void testDeleteCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setClienteId("12345");
        cliente.setContraseña("password");
        cliente.setEstado("Activo");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        clienteService.deleteCliente(1L);

        verify(clienteRepository, times(1)).delete(cliente);
    }
}
