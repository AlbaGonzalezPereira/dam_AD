package entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author alba_
 */

@Entity
@Table(name="empleado")
public class Empleado implements Serializable{
    @Id
    @Column(name="dni", columnDefinition = "char")
    private String dni;
    @Column(name="nombre")
    private String nombre;
    
    @ManyToMany(cascade=CascadeType.ALL)//para unir con empleados
    @JoinTable(name="asig_proyecto", joinColumns=@JoinColumn(name="dni_emp"),inverseJoinColumns=@JoinColumn(name="id_proyecto"))
    private List<Proyecto> proyectos;

    public Empleado() {
    }

    public Empleado(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public Empleado(String dni, String nombre, List<Proyecto> proyectos) {
        this.dni = dni;
        this.nombre = nombre;
        this.proyectos = proyectos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.dni);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        return Objects.equals(this.dni, other.dni);
    }

    @Override
    public String toString() {
        return "Empleado: " + "dni=" + dni + ", nombre=" + nombre;
    }
   
    
}
