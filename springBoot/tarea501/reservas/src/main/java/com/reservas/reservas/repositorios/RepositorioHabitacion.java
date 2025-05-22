package com.reservas.reservas.repositorios;

import com.reservas.reservas.entidades.Habitacion;
import com.reservas.reservas.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioHabitacion extends JpaRepository<Habitacion,Integer> {

    @Query("Select h from Habitacion h where h.hotel = :hotel ")
    List<Habitacion> findByHabitacionHotel(@Param("hotel") Hotel hotel);
}