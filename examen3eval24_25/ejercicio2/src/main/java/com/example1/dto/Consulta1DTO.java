package com.example1.dto;

import com.example1.entities.Libro;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;

@Data
public class Consulta1DTO {
    private int idLibro;
    private String titulo;
    private String autor;
    private int anhoPublicacion;
    private String nombreSocio;
    private String direccionSocio;

    public Consulta1DTO(Libro l, String nombreSocio, String direccionSocio){
        this.idLibro = l.getIdLibro();
        this.titulo = l.getTitulo();
        this.autor = l.getAutor();
        this.anhoPublicacion = l.getAnhoPublicacion();
        this.nombreSocio = nombreSocio;
        this.direccionSocio = direccionSocio;
    }
}
