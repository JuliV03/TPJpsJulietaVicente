package com.TrabajoJPA.demo.repositorio;

import com.TrabajoJPA.demo.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Pedido, Long> {
}
