package com.comentarios.comentarios.DTO;

import lombok.Data;

@Data
public class DTOComentarioHotel {
    private String nombreHotel;
    private String nombre; //nombre usuario
    private String contrasena;

    public DTOComentarioHotel() {
    }

    public DTOComentarioHotel(String nombreHotel, String nombre, String contrasena) {
        this.nombreHotel = nombreHotel;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public String getNombreHotel() {
        return nombreHotel;
    }

    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
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
