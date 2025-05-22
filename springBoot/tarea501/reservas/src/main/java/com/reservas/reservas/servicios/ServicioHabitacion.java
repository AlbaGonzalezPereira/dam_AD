package com.reservas.reservas.servicios;

import com.reservas.reservas.entidades.Habitacion;
import com.reservas.reservas.entidades.Hotel;
import com.reservas.reservas.repositorios.RepositorioHabitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioHabitacion {
    @Autowired
    private RepositorioHabitacion repoHabit;

    /**
     * servicio que crea una habitación
     * @param nuevaHabitacion
     * @return
     */
    public String crearHabitacion(Habitacion nuevaHabitacion) {
        Habitacion habitacionCreada = repoHabit.save(nuevaHabitacion);
        if (habitacionCreada.habitacion_id == 0) {
            return "Habitación no creada";
        }
        return "Habitación creada correctamente";
    }

    /**
     * servicio que actualiza una habitación en función de su id
     * @param habitacion
     * @return
     */
    public String actualizarHabitacion(Habitacion habitacion) {
        try {
            // Verificamos si la habitación existe en la base de datos
            if (habitacion.habitacion_id == 0) { //id inválido
                return null;
            }

            //buscamos el id en la base de datos
            Optional<Habitacion> habitacionBd = repoHabit.findById(habitacion.habitacion_id);
            if (habitacionBd.isPresent()) { //si existe en la base de datos
                // Actualizamos los campos de la habitación
                if (habitacion.hotel != null && habitacion.hotel.hotel_id != 0) {
                    habitacionBd.get().hotel = habitacion.hotel;
                }
                if (habitacion.numero_habitacion != 0) {
                    habitacionBd.get().numero_habitacion = habitacion.numero_habitacion;
                }
                if (habitacion.tipo != null && !habitacion.tipo.isEmpty()) {
                    habitacionBd.get().tipo = habitacion.tipo;
                }
                if (habitacion.precio != null) {
                    habitacionBd.get().precio = habitacion.precio;
                }
                habitacionBd.get().disponible = habitacion.disponible;

                // Actualizamos la habitación
                repoHabit.save(habitacionBd.get());
                //devolvemos un string
                return "Habitación actualizada correctamente";
            } else {
                return "Habitación no encontrada"; // Si no existe en la base de datos
            }
        } catch (Exception ex) {
            return "Introduce datos correctos";
        }
    }

    /**
     * servicio que elimina una habitación en función de un id
     * @param id
     * @return
     */
    public String eliminarHabitacion(int id){
        try {
            // Verificamos si la habitación existe en la base de datos
            if (id == 0) { //id inválido
                return null;
            }

            //buscamos el id en la base de datos
            Optional<Habitacion> habitacionBd = repoHabit.findById(id);
            if (habitacionBd.isPresent()) { //si existe en la base de datos
                // Eliminamos la habitación
                repoHabit.delete(habitacionBd.get());
                //devolvemos un string
                return "Habitación eliminada correctamente";
            } else {
                return "Habitación no encontrada"; // Si no existe en la base de datos
            }
        } catch (Exception ex) {
            return "Introduce datos correctos";
        }

    }

    public List<Habitacion> obtenerHabitacionByHotel(Hotel hotel) {
        List<Habitacion> listaHabit = repoHabit.findByHabitacionHotel(hotel);
        return listaHabit;
    }
}
