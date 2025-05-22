package com.reservas.reservas.DTO;

import com.reservas.reservas.entidades.Habitacion;
import lombok.Data;

@Data
public class DTOHabitacion {
    private Habitacion habitacion;
    private String nombre;
    private String contrasena;

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
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
