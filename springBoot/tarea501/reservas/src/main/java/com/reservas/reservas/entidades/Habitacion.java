package com.reservas.reservas.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Builder
@Data
@Table(name="habitacion")
@AllArgsConstructor
@NoArgsConstructor
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int habitacion_id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    public Hotel hotel;

    public int numero_habitacion;
    public String tipo;
    public BigDecimal precio;
    public boolean disponible;

    @OneToMany(mappedBy = "habitacion")
    public List<Reserva> reserva;
}
