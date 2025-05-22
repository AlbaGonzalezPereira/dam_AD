package com.comentarios.comentarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class DTOComentarioDelete {
    private String comentarioId;
    private String nombre; //nombre usuario
    private String contrasena;

    public DTOComentarioDelete() {
    }

    public DTOComentarioDelete(String comentarioId, String nombre, String contrasena) {
        this.comentarioId = comentarioId;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public String getComentarioId() {
        return comentarioId;
    }

    public void setComentarioId(String comentarioId) {
        this.comentarioId = comentarioId;
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
