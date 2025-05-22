package com.reservas.reservas.repositorios;

import com.reservas.reservas.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioHotel extends JpaRepository<Hotel,Integer> {
    @Query("Select h from Hotel h where h.nombre = :nom")
    Hotel findByNombre(@Param("nom") String nombre);
}
