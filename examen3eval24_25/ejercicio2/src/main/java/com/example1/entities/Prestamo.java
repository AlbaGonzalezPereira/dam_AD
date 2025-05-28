package com.example1.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="prestamos")
@EqualsAndHashCode(exclude = {"socio", "libro"})
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo")
    private int idPrestamo;

    @NonNull
    @Column(name = "fecha_prestamo", columnDefinition = "DATE")
    private LocalDate fechaPrestamo;

    @NonNull
    @Column(name = "fecha_devolucion", columnDefinition = "DATE")
    private LocalDate fechaDevolucion;

    @ManyToOne
    @JoinColumn(name = "id_socio")
    private Socio socio;

    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libro libro;

    public Prestamo(LocalDate fechaPrestamo){
        this.fechaPrestamo = fechaPrestamo;
    }

    public void anhadirLibro(Libro libro) {
        this.libro = libro;
        libro.addPrestamo(this);
    }

    public void anhadirSocio(Socio socio) {
        this.socio = socio;
        socio.addPrestamo(this);
    }
}
