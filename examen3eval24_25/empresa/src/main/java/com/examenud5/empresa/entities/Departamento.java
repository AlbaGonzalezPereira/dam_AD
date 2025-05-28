package com.examenud5.empresa.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String nombre;

    @OneToOne(mappedBy = "departamento")
    private Empleado empleado;

}
