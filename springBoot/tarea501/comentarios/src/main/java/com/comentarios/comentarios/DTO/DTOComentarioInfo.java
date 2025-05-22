package com.comentarios.comentarios.DTO;

import lombok.Data;

@Data
public class DTOComentarioInfo {
    private String nombreHotel;
    private int reservaId;
    private double puntuacion;
    private String comentario;

    public DTOComentarioInfo() {
    }

    public DTOComentarioInfo(String nombreHotel, int reservaId, double puntuacion, String comentario) {
        this.nombreHotel = nombreHotel;
        this.reservaId = reservaId;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    public String getNombreHotel() {
        return nombreHotel;
    }

    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }

    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
