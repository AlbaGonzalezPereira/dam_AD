package com.reservas.reservas.servicios;

import com.reservas.reservas.DTO.DTOReservaUsuario;
import com.reservas.reservas.entidades.Reserva;
import com.reservas.reservas.repositorios.RepositorioReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioReserva {
    @Autowired
    private RepositorioReserva repoReserv;

    public String crearReserva(Reserva nuevaReserva) {
        Reserva reservaCreada = repoReserv.save(nuevaReserva);
        if (reservaCreada.getReserva_id() == 0) {
            return "Reserva no creada";
        }
        return "Reserva creada correctamente";
    }

    /**
     * método servicio que cambia el estado de una reserva
     * @param reserva
     * @return
     */
    public String cambiarEstado(Reserva reserva) {
        try {
            // Verificamos si la reserva existe en la base de datos
            if (reserva.getReserva_id() == 0) { //id inválido
                return null;
            }

            //buscamos el id en la base de datos
            Optional<Reserva> reservaBd = repoReserv.findById(reserva.getReserva_id());
            if (reservaBd.isPresent()) { //si existe en la base de datos
                // Actualizamos el estado de la reserva
                if (reserva.getEstado() != null) {
                    reservaBd.get().setEstado(reserva.getEstado());
                }

                // Actualizamos la reserva
                repoReserv.save(reservaBd.get());
                //devolvemos un string
                return "Estado de la reserva actualizado correctamente";
            } else {
                return "Reserva no encontrada"; // Si no existe en la base de datos
            }
        } catch (Exception ex) {
            return "Introduce datos correctos";
        }
    }


    public List<DTOReservaUsuario> listarReservasUsuario(Integer idUsuario) {
        List<Reserva> reservas = repoReserv.findByUsuario_id(idUsuario);
        List<DTOReservaUsuario> reservasUsuarios = new ArrayList<>();

        if(reservas == null){
            return null;
        }

        for (Reserva reserva : reservas) {
            DTOReservaUsuario reservaUsuario = new DTOReservaUsuario(reserva.getFecha_inicio(), reserva.getFecha_fin(), reserva.getHabitacion().habitacion_id);
            reservasUsuarios.add(reservaUsuario);
        }

        return reservasUsuarios;
    }

    public List<DTOReservaUsuario> listarReservasSegunEstado(String estado) {
        List<Reserva> reservas = repoReserv.findByEstado(estado);
        List<DTOReservaUsuario> reservasUsuarios = new ArrayList<>();

        if(reservas == null){
            return null;
        }

        for (Reserva reserva : reservas) {
            DTOReservaUsuario reservaUsuario = new DTOReservaUsuario(reserva.getFecha_inicio(), reserva.getFecha_fin(), reserva.getHabitacion().habitacion_id);
            reservasUsuarios.add(reservaUsuario);
        }

        return reservasUsuarios;
    }

    public Boolean checkReserva(Integer idUsuario, Integer idHabitacion, Integer idReserva) {
        Reserva reserva = repoReserv.findByReserva(idUsuario, idHabitacion, idReserva);
        if (reserva == null){
            return false;
        }
        return true;
    }
}
