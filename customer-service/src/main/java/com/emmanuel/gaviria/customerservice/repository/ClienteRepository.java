package com.emmanuel.gaviria.customerservice.repository;

import com.emmanuel.gaviria.customerservice.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
