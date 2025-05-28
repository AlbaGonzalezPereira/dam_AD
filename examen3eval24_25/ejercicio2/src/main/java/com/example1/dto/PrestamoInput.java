package com.example1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoInput {
    private int idLibro;
    private int idSocio;
    private String fechaPrestamo;
    private String fechaDevolucion;
}
