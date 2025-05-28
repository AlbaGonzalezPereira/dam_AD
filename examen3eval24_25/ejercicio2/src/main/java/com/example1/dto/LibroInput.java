package com.example1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroInput {

    private String titulo;
    private String autor;
    private int anoPublicacion;
}
