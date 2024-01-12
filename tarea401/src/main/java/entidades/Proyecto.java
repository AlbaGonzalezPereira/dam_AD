package entidades;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author alba_
 */
@Entity
@Table(name="proyecto")
public class Proyecto {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="nombre")
    private String nombreProyecto;
    @Column(name="fecha_inicio")
    private LocalDate fechaInicio;
    @Column(name="fecha_fin")
    private LocalDate fechaFin;
    
    @ManyToOne 
    // Indicamos que el nombre de la columna en la tabla
    @JoinColumn(name = "dni_jefe_proyecto")
    private Empleado jefeProyecto;
    
    @ManyToMany(mappedBy="proyectos")
    private List<Empleado> empleados;//la inicializa el hibernate

    public Proyecto() {
    }

    public Proyecto(int id, String nombreProyecto, LocalDate fechaInicio, LocalDate fechaFin, Empleado jefeProyecto) {
        this.id = id;
        this.nombreProyecto = nombreProyecto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.jefeProyecto = jefeProyecto;
    }

    public Proyecto(String nombreProyecto, LocalDate fechaInicio, Empleado jefeProyecto) {
        this.nombreProyecto = nombreProyecto;
        this.fechaInicio = fechaInicio;
        this.jefeProyecto = jefeProyecto;
    }

    public Proyecto(String nombreProyecto, LocalDate fechaInicio, LocalDate fechaFin, Empleado jefeProyecto) {
        this.nombreProyecto = nombreProyecto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.jefeProyecto = jefeProyecto;
    }

    public Proyecto(int id, String nombreProyecto, LocalDate fechaInicio, LocalDate fechaFin, Empleado jefeProyecto, List<Empleado> empleados) {
        this.id = id;
        this.nombreProyecto = nombreProyecto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.jefeProyecto = jefeProyecto;
        this.empleados = empleados;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
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

    public Empleado getJefeProyecto() {
        return jefeProyecto;
    }

    public void setJefeProyecto(Empleado jefeProyecto) {
        this.jefeProyecto = jefeProyecto;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "Proyecto: " + "id=" + id + ", nombreProyecto=" + nombreProyecto + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", jefeProyecto=" + jefeProyecto + ", empleados=" + empleados;
    }

    

    
    
    
    
    
}
