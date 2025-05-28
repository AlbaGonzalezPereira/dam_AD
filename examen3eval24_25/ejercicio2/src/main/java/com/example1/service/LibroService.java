package com.example1.service;

import com.example1.dto.Consulta1DTO;
import com.example1.dto.Consulta2DTO;
import com.example1.dto.Consulta8DTO;
import com.example1.entities.*;
import com.example1.repositories.LibroRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibroService {

    private final LibroRespository libroRespository;


    public Libro crearLibro(String titulo, String autor, int anoPublicacion) {
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setAnhoPublicacion(anoPublicacion);
        return libroRespository.save(libro);
    }

    public List<Consulta1DTO> consulta1() {
        List<Object[]> resultadoConsulta = libroRespository.consulta1();
        return resultadoConsulta.stream().map(obj -> new Consulta1DTO((Libro) obj[0], (String) obj[1], (String) obj[2])).collect(Collectors.toList());
    }

    public List<Consulta2DTO> consulta2() {
        List<Object[]> resultadoConsulta = libroRespository.consulta2();
        return resultadoConsulta.stream().map(obj -> new Consulta2DTO((String) obj[0], (String) obj[1], (LocalDate) obj[2], (LocalDate) obj[3])).collect(Collectors.toList());
    }

    public List<Consulta8DTO> consulta8(LocalDate fecha1, LocalDate fecha2) {
        List<Object[]> resultadoConsulta = libroRespository.consulta8(fecha1, fecha2);
        return resultadoConsulta.stream().map(obj -> new Consulta8DTO((String) obj[0], (LocalDate) obj[1], (LocalDate) obj[2])).collect(Collectors.toList());
    }
    public List<Consulta8DTO> consulta9(int idSocio) {
        List<Object[]> resultadoConsulta = libroRespository.consulta9(idSocio);
        return resultadoConsulta.stream().map(obj -> new Consulta8DTO((String) obj[0], (LocalDate) obj[1], (LocalDate) obj[2])).collect(Collectors.toList());
    }
}
