package com.comentarios.comentarios.entidad;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection="comentarios")
public class Comentarios {
    @Id
    private String id;
    private int usuarioId;
    private int hotelId;
    private int reservaId;
    private double puntuacion;
    private String comentario;
    private LocalDateTime fechaCreacion;

    public Comentarios(String id, int usuarioId, int hotelId, int reservaId, double puntuacion, String comentario, LocalDateTime fechaCreacion) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.hotelId = hotelId;
        this.reservaId = reservaId;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fechaCreacion = fechaCreacion;
    }

    public Comentarios(int usuarioId, int hotelId, int reservaId, double puntuacion, String comentario, LocalDateTime fechaCreacion) {
        this.usuarioId = usuarioId;
        this.hotelId = hotelId;
        this.reservaId = reservaId;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fechaCreacion = fechaCreacion;
    }

    public Comentarios() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
