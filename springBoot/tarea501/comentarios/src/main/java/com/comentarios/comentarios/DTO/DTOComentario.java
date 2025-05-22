package com.comentarios.comentarios.DTO;

public class DTOComentario {
    private String nombreHotel;
    private int reservaId;
    private double puntuacion;
    private String comentario;
    private String nombre; //nombre usuario
    private String contrasena;

    public DTOComentario() {
    }

    public DTOComentario(String nombreHotel, int reservaId, double puntuacion, String comentario, String nombre, String contrasena) {
        this.nombreHotel = nombreHotel;
        this.reservaId = reservaId;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.nombre = nombre;
        this.contrasena = contrasena;
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
