package com.reservas.reservas.DTO;

import java.time.LocalDate;

public class DTOReservaUsuario {
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private int habitacion_id;

    public DTOReservaUsuario() {
    }

    public DTOReservaUsuario(LocalDate fecha_inicio, LocalDate fecha_fin, int habitacion_id) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.habitacion_id = habitacion_id;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getHabitacion_id() {
        return habitacion_id;
    }

    public void setHabitacion_id(int habitacion_id) {
        this.habitacion_id = habitacion_id;
    }
}
