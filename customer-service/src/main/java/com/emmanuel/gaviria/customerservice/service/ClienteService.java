package com.emmanuel.gaviria.customerservice.service;

import com.emmanuel.gaviria.customerservice.entity.Cliente;
import com.emmanuel.gaviria.customerservice.repository.ClienteRepository;
import com.emmanuel.gaviria.customerservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        cliente.setClienteId(clienteDetails.getClienteId());
        cliente.setContraseña(clienteDetails.getContraseña());
        cliente.setEstado(clienteDetails.getEstado());
        cliente.setNombre(clienteDetails.getNombre());
        cliente.setGenero(clienteDetails.getGenero());
        cliente.setEdad(clienteDetails.getEdad());
        cliente.setIdentificacion(clienteDetails.getIdentificacion());
        cliente.setDireccion(clienteDetails.getDireccion());
        cliente.setTelefono(clienteDetails.getTelefono());
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        clienteRepository.delete(cliente);
    }
}
