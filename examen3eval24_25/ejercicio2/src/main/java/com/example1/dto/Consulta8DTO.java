package com.example1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Consulta8DTO {
    private String titulo;
    private LocalDate fechaPrestamo;
    private LocalDate fechDevolucion;
}
