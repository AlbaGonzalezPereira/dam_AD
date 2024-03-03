package bbdd;

/**
 *
 * @author alba_
 */
public class Equipo {
    private int equipo_id;
    private String nombre;
    private String ciudad;
    private Persona entrenador;

    public Equipo() {
    }
    
    public Equipo(int equipo_id, String nombre, String ciudad, Persona entrenador) {
        this.equipo_id = equipo_id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.entrenador = entrenador;
    }

    public Equipo(String nombre, String ciudad, Persona entrenador) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.entrenador = entrenador;
    }

    public int getEquipo_id() {
        return equipo_id;
    }

    public void setEquipo_id(int equipo_id) {
        this.equipo_id = equipo_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Persona getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Persona entrenador) {
        this.entrenador = entrenador;
    }

    @Override
    public String toString() {
        return "Equipo: " + "equipo_id=" + equipo_id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", entrenador=" + entrenador;
    }
    
    
}
