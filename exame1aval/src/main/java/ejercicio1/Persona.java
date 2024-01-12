package ejercicio1;

/**
 *
 * @author alba_
 */
public class Persona {
    private String nombre;
    private String apellido1;
    private String apellido2;

    //insertamos constructor
    public Persona(String nombre, String apellido1, String apellido2) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    //insertamos getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    //insertamos método toString
    @Override
    public String toString() {
        return nombre + " " + apellido1 + " " + apellido2;
    }
    
    
    
    
}
