package com.example1;

import com.example1.dto.*;
import com.example1.entities.*;
import com.example1.service.LibroService;
import com.example1.service.SocioService;
import com.example1.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private SocioService socioService;
    @Autowired
    private PrestamoService prestamoService;
    @Autowired
    private LibroService libroService;

    @QueryMapping
    public List<Consulta1DTO> consulta1(){
        return libroService.consulta1();
    }

    @QueryMapping
    public List<Consulta2DTO> consulta2(){
        return libroService.consulta2();
    }


    @QueryMapping
    public List<Consulta8DTO> consulta8(@Argument String fecha1, @Argument String fecha2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate f1 = LocalDate.parse(fecha1, formatter);
        LocalDate f2 = LocalDate.parse(fecha2, formatter);
        return libroService.consulta8(f1, f2);
    }

    @QueryMapping
    public List<Consulta8DTO> consulta9(@Argument int id){
        return libroService.consulta9(id);
    }

    @MutationMapping
    public Libro crearLibro(@Argument LibroInput input) {
        return libroService.crearLibro(
                input.getTitulo(),
                input.getAutor(),
                input.getAnoPublicacion()
        );
    }

    @MutationMapping
    public Prestamo crearPrestamo(@Argument PrestamoInput input) {
        return prestamoService.crearPrestamo(
                input.getIdLibro(),
                input.getIdSocio(),
                LocalDate.parse(input.getFechaPrestamo()),
                LocalDate.parse(input.getFechaDevolucion())
        );
    }

}
