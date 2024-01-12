package ejercicio2;

/**
 *
 * @author alba_
 */
public class Usuario {
    private String nombre;
    private String contrasinal;

    public Usuario(String nombre, String contrasinal) {
        this.nombre = nombre;
        this.contrasinal = contrasinal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasinal() {
        return contrasinal;
    }

    public void setContrasinal(String contrasinal) {
        this.contrasinal = contrasinal;
    }

    @Override
    public String toString() {
        return "nombre=" + nombre + ", contrasinal=" + contrasinal;
    }
    
    
    
}
