package com.reservas.reservas.repositorios;

import com.reservas.reservas.entidades.Hotel;
import com.reservas.reservas.entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioReserva extends JpaRepository<Reserva,Integer> {
    @Query("Select h from Hotel h where h.nombre = :nom")
    Hotel findByNombre(@Param("nom") String nombre);

    @Query("Select r from Reserva r where r.usuario_id = :idUsu")
    List<Reserva> findByUsuario_id(@Param("idUsu") Integer idUsuario);

    //no hace falta el @Query porque ya lo detecta automáticamente
    List<Reserva> findByEstado(String estado);

    @Query("Select r from Reserva r where r.usuario_id = :idUsu and r.habitacion.habitacion_id = :idHabit and r.reserva_id = :idRes")
    Reserva findByReserva(@Param("idUsu") Integer idUsuario, @Param("idHabit") Integer idHabitacion, @Param("idRes") Integer idReserva);
}

