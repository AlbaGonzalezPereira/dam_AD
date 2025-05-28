package com.examenud5.empresa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "proyecto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String titulo;

    @NonNull
    @Column(name = "anho_inicio")
    private int anho;

    @ManyToMany
    @JoinTable(name = "empleado_proyecto",
            joinColumns = @JoinColumn(name = "proyecto_id"),
            inverseJoinColumns =  @JoinColumn(name = "empleado_id"))
    private List<Empleado> listaEmpleados;
}
