package com.example1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Consulta2DTO {
    private String titulo;
    private String nombreSocio;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

}
