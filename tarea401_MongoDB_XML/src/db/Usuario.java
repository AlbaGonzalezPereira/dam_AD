
package db;

/**
 *
 * @author alba_
 */
public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private int edad;
    private String direccion;

    public Usuario() {
    }

    public Usuario(String id, String nombre, int edad, String direccion ) {
        this.id = id;
        this.nombre = nombre;
        this.email = id;
        this.edad = edad;
        this.direccion = direccion;
       
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Usuario: " + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", edad=" + edad + ", direccion=" + direccion;
    }
    
    
    
}
