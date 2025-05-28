package com.example1.service;

import com.example1.entities.Libro;
import com.example1.entities.Prestamo;
import com.example1.entities.Socio;
import com.example1.repositories.LibroRespository;
import com.example1.repositories.PrestamoRepository;
import com.example1.repositories.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PrestamoService {

    private final PrestamoRepository prestamoRespository;
    private final LibroRespository libroRespository;
    private final SocioRepository socioRepository;

    public Prestamo crearPrestamo(int idLibro, int idSocio, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        Libro libro = libroRespository.findById(idLibro)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Socio socio = socioRepository.findById(idSocio)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));

        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(libro);
        prestamo.setSocio(socio);
        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaDevolucion(fechaDevolucion);
        return prestamoRespository.save(prestamo);
    }

}
