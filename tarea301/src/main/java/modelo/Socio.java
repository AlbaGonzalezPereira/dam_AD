package modelo;

/**
 * clase que hace referencia a la tabla socios de la base de datos donde 
 * muestra el registro de todos los socios
 * @author alba_
 */
public class Socio {
    private String dni;
    private String nombre;
    private String apellidos;

    //insertamos el constructor
    public Socio(String dni, String nombre, String apellidos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    //insertamos getters y setters
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
}
