package com.emmanuel.gaviria.accountservice.client;

import com.emmanuel.gaviria.accountservice.dto.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerClient {
    @Value("${customer.service.url}")
    private String customerServiceUrl;

    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Cliente getClienteById(Long clienteId) {
        return restTemplate.getForObject(customerServiceUrl + "/api/clientes/" + clienteId, Cliente.class);
    }
}