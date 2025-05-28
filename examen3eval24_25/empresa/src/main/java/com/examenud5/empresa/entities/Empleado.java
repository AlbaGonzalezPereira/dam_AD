package com.examenud5.empresa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "empleado")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String nombre;

    @OneToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @ManyToMany(mappedBy = "listaEmpleados")
    private List<Proyecto> listaProyectos;
}
