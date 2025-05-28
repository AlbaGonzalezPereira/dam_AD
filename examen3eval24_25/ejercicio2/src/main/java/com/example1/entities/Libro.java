package com.example1.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="libros")
@EqualsAndHashCode(exclude = {"listaPrestamo"})
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private int idLibro;

    @NonNull
    @Column(length = 100)
    private String titulo;

    @NonNull
    @Column(length = 50)
    private String autor;

    @NonNull
    @Column(name = "ano_publicacion")
    private int anhoPublicacion;

    @OneToMany(mappedBy = "libro")
    private List<Prestamo> listaPrestamo = new ArrayList<>();

    public void addPrestamo(Prestamo prestamo) {
        this.listaPrestamo.add(prestamo);
    }
}
