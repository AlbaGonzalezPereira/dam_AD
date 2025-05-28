package com.example1.repositories;

import com.example1.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LibroRespository extends JpaRepository<Libro, Integer> {

    @Query("select l, s.nombre, s.direccion from Libro l JOIN Prestamo p ON l.idLibro = p.libro.idLibro JOIN Socio s ON p.socio.idSocio = s.idSocio")
    public List<Object[]> consulta1();

    @Query("select l.titulo, s.nombre, p.fechaPrestamo, p.fechaDevolucion " +
            "from Libro l JOIN Prestamo p ON l.idLibro = p.libro.idLibro " +
            "JOIN Socio s ON p.socio.idSocio = s.idSocio " +
            "where p.fechaDevolucion is null")
    public List<Object[]> consulta2();

    @Query("select l.titulo, p.fechaPrestamo, p.fechaDevolucion from Libro l" +
            " JOIN Prestamo p ON l.idLibro = p.libro.idLibro " +
            "where p.fechaPrestamo between :fecha1 and :fecha2")
    public List<Object[]> consulta8(@Param("fecha1")LocalDate fecha, @Param("fecha2") LocalDate fecha2);

    @Query("select l.titulo, p.fechaPrestamo, p.fechaDevolucion from Libro l" +
            " JOIN Prestamo p ON l.idLibro = p.libro.idLibro " +
            "JOIN Socio s ON p.socio.idSocio = s.idSocio " +
            "where s.idSocio = ?1")
    public List<Object[]> consulta9(int idSocio);
}
