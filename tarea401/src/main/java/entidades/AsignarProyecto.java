
package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author alba_
 */
@Entity
@Table(name="asig_proyecto")
public class AsignarProyecto implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name="dni_emp",columnDefinition = "char")
    private Empleado empleado;
    
    @Id
    @ManyToOne
    @JoinColumn(name="id_proyecto")
    private Proyecto proyecto;
    @Column(name="fecha_inicio")
    private LocalDate fechaInicio;
    @Column(name="fecha_fin")
    private LocalDate fechaFin;

    public AsignarProyecto() {
    }

    public AsignarProyecto(Empleado empleado, Proyecto proyecto, LocalDate fechaInicio, LocalDate fechaFin) {
        this.empleado = empleado;
        this.proyecto = proyecto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.empleado);
        hash = 43 * hash + Objects.hashCode(this.proyecto);
        hash = 43 * hash + Objects.hashCode(this.fechaInicio);
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
        final AsignarProyecto other = (AsignarProyecto) obj;
        if (!Objects.equals(this.empleado, other.empleado)) {
            return false;
        }
        if (!Objects.equals(this.proyecto, other.proyecto)) {
            return false;
        }
        return Objects.equals(this.fechaInicio, other.fechaInicio);
    }

    
    @Override
    public String toString() {
        return "AsignarProyecto: " + "empleado=" + empleado + ", proyecto=" + proyecto + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin;
    }
    
    
    
}
