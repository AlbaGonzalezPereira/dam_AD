import java.io.Serializable;

/**
 *
 * @author alba_
 */
public class Alumno implements Serializable {
    private String nombre;
    private String apellidos;

    public Alumno() {
    }

    public Alumno(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Alumno:" + "nombre=" + nombre + ", apellidos=" + apellidos;
    } 
}
