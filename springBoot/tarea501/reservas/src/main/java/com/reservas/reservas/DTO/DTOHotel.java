package com.reservas.reservas.DTO;
import com.reservas.reservas.entidades.Hotel;

public class DTOHotel {
    private Hotel hotel;
    private String nombre;
    private String contrasena;

    public DTOHotel() {
    }

    public DTOHotel(Hotel hotel, String nombre, String contrasena) {
        this.hotel = hotel;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
