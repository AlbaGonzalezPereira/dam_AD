package com.examenud5.empresa.repositorio;

import com.examenud5.empresa.entities.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioProyecto extends JpaRepository<Proyecto, Integer> {

    List<Proyecto> findByAnho(Integer anhoInicio);


}
