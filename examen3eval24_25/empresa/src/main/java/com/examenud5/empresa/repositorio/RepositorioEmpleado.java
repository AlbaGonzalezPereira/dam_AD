package com.examenud5.empresa.repositorio;

import com.examenud5.empresa.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEmpleado extends JpaRepository<Empleado, Integer> {
}
