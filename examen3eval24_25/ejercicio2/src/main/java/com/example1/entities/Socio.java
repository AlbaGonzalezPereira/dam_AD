package com.example1.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "socios")
@EqualsAndHashCode(exclude = {"listaPrestamos"})
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_socio")
    private int idSocio;

    @NonNull
    @Column(length = 50)
    private String nombre;

    @NonNull
    @Column(length = 100)
    private String direccion;

    @NonNull
    @Column(name = "fecha_inscripcion", columnDefinition = "DATE")
    private LocalDate fechaInscripcion;

    @OneToMany(mappedBy = "socio")
    private List<Prestamo> listaPrestamos = new ArrayList<>();

    public void addPrestamo(Prestamo prestamo) {
        this.listaPrestamos.add(prestamo);
    }
}
