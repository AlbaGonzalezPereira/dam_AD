package com.reservas.reservas.DTO;

import com.reservas.reservas.entidades.Reserva;

public class DTOReserva {
    private Reserva reserva;
    private String nombre;
    private String contrasena;

    public DTOReserva(Reserva reserva, String nombre, String contrasena) {
        this.reserva = reserva;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public DTOReserva() {
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
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
