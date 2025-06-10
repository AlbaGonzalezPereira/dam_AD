
package bbdd;

/**
 *
 * @author alba_
 */
public class Jugadores {
    private int jugador_id;
    private Persona datos_personales;
    private Jugador jugador_info;
    private Equipo equipo;

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Jugadores(Persona datos_personales, Jugador jugador_info) {
        this.datos_personales = datos_personales;
        this.jugador_info = jugador_info;
    }

    public Jugadores(int jugador_id, Persona datos_personales, Jugador jugador_info) {
        this.jugador_id = jugador_id;
        this.datos_personales = datos_personales;
        this.jugador_info = jugador_info;
    }

    public int getJugador_id() {
        return jugador_id;
    }

    public void setJugador_id(int jugador_id) {
        this.jugador_id = jugador_id;
    }

    public Persona getDatos_personales() {
        return datos_personales;
    }

    public void setDatos_personales(Persona datos_personales) {
        this.datos_personales = datos_personales;
    }

    public Jugador getJugador_info() {
        return jugador_info;
    }

    public void setJugador_info(Jugador jugador_info) {
        this.jugador_info = jugador_info;
    }

    @Override
    public String toString() {
        return "Jugadores: " + "jugador_id=" + jugador_id + ", datos_personales=" + datos_personales + ", jugador_info=" + jugador_info + ", equipo=" + equipo;
    }

  
    
    
}
