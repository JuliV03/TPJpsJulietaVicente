package com.TrabajoJPA.demo.repositorio;

import com.TrabajoJPA.demo.entidades.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio,  Long> {
}
