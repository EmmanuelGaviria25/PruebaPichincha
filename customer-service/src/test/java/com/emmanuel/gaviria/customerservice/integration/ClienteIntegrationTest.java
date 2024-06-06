package com.emmanuel.gaviria.customerservice.integration;

import com.emmanuel.gaviria.customerservice.entity.Cliente;
import com.emmanuel.gaviria.customerservice.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setup() {
        clienteRepository.deleteAll();
    }

    @Test
    public void testCreateCliente() throws Exception {
        String clienteJson = "{\"nombre\":\"Maria\",\"genero\":\"Femenino\",\"edad\":25,\"identificacion\":\"87654321\",\"direccion\":\"Avenida 456\",\"telefono\":\"555-5678\",\"clienteId\":\"maria123\",\"contraseña\":\"password\",\"estado\":\"Activo\"}";

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").value("maria123"));
    }

    @Test
    public void testGetClienteById() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setClienteId("maria123");
        cliente.setContraseña("password");
        cliente.setEstado("Activo");
        cliente.setNombre("Maria");
        cliente.setGenero("Femenino");
        cliente.setEdad(25);
        cliente.setIdentificacion("87654321");
        cliente.setDireccion("Avenida 456");
        cliente.setTelefono("555-5678");
        clienteRepository.save(cliente);

        mockMvc.perform(get("/api/clientes/{id}", cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").value("maria123"));
    }

    @Test
    public void testUpdateCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setClienteId("maria123");
        cliente.setContraseña("password");
        cliente.setEstado("Activo");
        cliente.setNombre("Maria");
        cliente.setGenero("Femenino");
        cliente.setEdad(25);
        cliente.setIdentificacion("87654321");
        cliente.setDireccion("Avenida 456");
        cliente.setTelefono("555-5678");
        clienteRepository.save(cliente);

        String updatedClienteJson = "{\"nombre\":\"Maria\",\"genero\":\"Femenino\",\"edad\":26,\"identificacion\":\"87654321\",\"direccion\":\"Avenida 456\",\"telefono\":\"555-5678\",\"clienteId\":\"maria123\",\"contraseña\":\"newpassword\",\"estado\":\"Inactivo\"}";

        mockMvc.perform(put("/api/clientes/{id}", cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedClienteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.edad").value(26))
                .andExpect(jsonPath("$.contraseña").value("newpassword"))
                .andExpect(jsonPath("$.estado").value("Inactivo"));
    }

    @Test
    public void testDeleteCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setClienteId("maria123");
        cliente.setContraseña("password");
        cliente.setEstado("Activo");
        cliente.setNombre("Maria");
        cliente.setGenero("Femenino");
        cliente.setEdad(25);
        cliente.setIdentificacion("87654321");
        cliente.setDireccion("Avenida 456");
        cliente.setTelefono("555-5678");
        clienteRepository.save(cliente);

        mockMvc.perform(delete("/api/clientes/{id}", cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
