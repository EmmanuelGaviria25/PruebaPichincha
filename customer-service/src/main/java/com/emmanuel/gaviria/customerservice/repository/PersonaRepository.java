package com.emmanuel.gaviria.customerservice.repository;

import com.emmanuel.gaviria.customerservice.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
