package com.reservas.reservas.servicios;

import com.reservas.reservas.entidades.Hotel;
import com.reservas.reservas.repositorios.RepositorioHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioHotel {

    @Autowired
    private RepositorioHotel repoHotel;

    public String crearHotel(Hotel nuevoHotel) {
        Hotel hotelCreado = repoHotel.save(nuevoHotel);
        if (hotelCreado.getHotel_id() == 0) {
            return "Hotel no creado";
        }
        return "Hotel creado correctamente";
    }

    public String actualizarHotel(Hotel hotel){
        try {
            // Verificamos si el hotel existe en la base de datos
            if (hotel.hotel_id == 0) { //id inválido
                return null;
            }

            //buscamos el id en la base de datos
            Optional<Hotel> hotelBd = repoHotel.findById(hotel.hotel_id);
            if (hotelBd.isPresent()) { //si existe en la base de datos
                // Actualizamos los campos de la habitación

                if (hotel.nombre != null) {
                    hotelBd.get().nombre = hotel.nombre;
                }
                if (hotel.direccion != null) {
                    hotelBd.get().direccion = hotel.direccion;
                }

                // Actualizamos el hotel
                repoHotel.save(hotelBd.get());
                //devolvemos un string
                return "Hotel actualizado correctamente";
            } else {
                return "Hotel no encontrado"; // Si no existe en la base de datos
            }
        } catch (Exception ex) {
            return "Introduce datos correctos";
        }
    }

    public String eliminarHotel(int id) {
        try {
            // Verificamos si el hotel existe en la base de datos
            if (id == 0) { //id inválido
                return null;
            }

            //buscamos el id en la base de datos
            Optional<Hotel> hotelBd = repoHotel.findById(id);
            if (hotelBd.isPresent()) { //si existe en la base de datos
                // Eliminamos el hotel
                repoHotel.delete(hotelBd.get());
                //devolvemos un String
                return "Hotel eliminado correctamente";
            } else {
                return "Hotel no encontrado"; // Si no existe en la base de datos
            }
        } catch (Exception ex) {
            return "Introduce datos correctos";
        }
    }

    public Integer obtenerIdApartirNombre(String nombre) {

        Hotel hotelInfo = repoHotel.findByNombre(nombre);
        if (hotelInfo != null) { //si no existe en la base de datos
            return hotelInfo.getHotel_id(); // Devolvemos el id del hotel
        } else {
            return null; // Si no existe en la base de datos, devolvemos null para que no lo cree
        }

    }

    public String obtenerNombreApartirId(Integer id) {
        Optional<Hotel> hotelBd = repoHotel.findById(id);
        if (hotelBd.isPresent()) { //si existe en la base de datos
            return hotelBd.get().nombre; // Delvemos el nombre del hotel
        } else {
            return null; // Si no existe en la base de datos, devolvemos null para que no lo cree
        }

    }

    /**
     *
     * @param idHotel
     * @return
     */
    public Hotel obtenerHotelById(int idHotel) {
        Optional<Hotel> hotelBd = repoHotel.findById(idHotel);
        if (hotelBd.isPresent()) { //si existe en la base de datos
            return hotelBd.get(); // Delvemos el nombre del hotel
        } else {
            return null; // Si no existe en la base de datos, devolvemos null para que no lo cree
        }
    }
}
