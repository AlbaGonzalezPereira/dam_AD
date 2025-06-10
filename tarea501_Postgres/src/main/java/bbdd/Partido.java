package bbdd;

import java.time.LocalDate;

/**
 *
 * @author alba_
 */
public class Partido {
    private int partido_id;
    private LocalDate fecha;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;

    public Partido(int partido_id, LocalDate fecha, Equipo equipoLocal, Equipo equipoVisitante) {
        this.partido_id = partido_id;
        this.fecha = fecha;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
    }

    public Partido(LocalDate fecha, Equipo equipoLocal, Equipo equipoVisitante) {
        this.fecha = fecha;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
    }
    
    

    public int getPartido_id() {
        return partido_id;
    }

    public void setPartido_id(int partido_id) {
        this.partido_id = partido_id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    @Override
    public String toString() {
        return "Partido: " + "partido_id=" + partido_id + ", fecha=" + fecha + ", equipoLocal=" + equipoLocal + ", equipoVisitante=" + equipoVisitante;
    }
    
}
