package entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author alba_
 */

@Entity
@Table(name="datos_profesionales")
public class DatosProfesionales implements Serializable{
    @Id
    @OneToOne
    @JoinColumn(name="dni", columnDefinition = "char")
    private Empleado empleado;
    @Column(name="categoria", columnDefinition = "enum")
    private String categoria;
    @Column(name="sueldo_bruto", columnDefinition = "decimal")
    private double sueldoBruto;

    public DatosProfesionales() {
    }

    public DatosProfesionales(Empleado empleado, String categoria, double sueldoBruto) {
        this.empleado = empleado;
        this.categoria = categoria;
        this.sueldoBruto = sueldoBruto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getSueldoBruto() {
        return sueldoBruto;
    }

    public void setSueldoBruto(double sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.empleado);
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
        final DatosProfesionales other = (DatosProfesionales) obj;
        return Objects.equals(this.empleado, other.empleado);
    }

    
    @Override
    public String toString() {
        return empleado + ", categoria=" + categoria + ", sueldoBruto=" + sueldoBruto;
    }
    
    
    
}
