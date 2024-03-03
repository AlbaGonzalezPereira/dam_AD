
package bbdd;

import static bbdd.EquipoDAO.recuperarEquipos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author alba_
 */
public class PartidoDAO {
    public static int insertarPartido(Partido partido, Connection con) {
        String sql = "INSERT INTO objetos.Partidos(fecha, equipo_local, equipo_visitante) VALUES(?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setDate(1, Date.valueOf(partido.getFecha()));
            stmt.setInt(2, partido.getEquipoLocal().getEquipo_id());
            stmt.setInt(3, partido.getEquipoVisitante().getEquipo_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1; 
    }

    public static int modificarPartido(Partido partido, Connection con) {
        String sql = "UPDATE objetos.Partidos SET fecha = ?, equipo_local = ?, equipo_visitante = ? WHERE partido_id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setDate(1, Date.valueOf(partido.getFecha()));
            stmt.setInt(2, partido.getEquipoLocal().getEquipo_id());
            stmt.setInt(3, partido.getEquipoVisitante().getEquipo_id());
            stmt.setInt(4, partido.getPartido_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1; 
    }

    public static int eliminarPartido(int partido_id, Connection con) {
        String sql = "DELETE FROM objetos.Partidos WHERE partido_id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, partido_id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1; 
    }
    
    public static ArrayList<Partido> buscarPartidosLocal(int equipo_id, Connection con){
        String sql ="SELECT * FROM objetos.partidos WHERE equipo_local = ?;";
        ArrayList<Partido> partidosLocal = new ArrayList<>();
        Partido p = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, equipo_id);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                 p = new Partido(resultado.getInt(1), resultado.getDate(2).toLocalDate(), EquipoDAO.recuperarEquipo(equipo_id, con), EquipoDAO.recuperarEquipo(resultado.getInt(4),con));
                 partidosLocal.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return partidosLocal;
    }
    
    public static void listarPartidosLocales(int equipo_id, Connection con){
        ArrayList<Partido> partidosLocales = buscarPartidosLocal(equipo_id, con);
        for (Partido partidosLocal : partidosLocales) {
            System.out.println(partidosLocal);     
        }
    }
    
     public static ArrayList<Partido> buscarPartidosVisitante(int equipo_id, Connection con){
        String sql ="SELECT * FROM objetos.partidos WHERE equipo_visitante = ?;";
        ArrayList<Partido> partidosVisitante = new ArrayList<>();
        Partido p = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, equipo_id);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                 p = new Partido(resultado.getInt(1), resultado.getDate(2).toLocalDate(), EquipoDAO.recuperarEquipo(resultado.getInt(3), con), EquipoDAO.recuperarEquipo(equipo_id,con));
                 partidosVisitante.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return partidosVisitante;
    }
    
    public static void listarPartidosVisitantes(int equipo_id, Connection con){
        ArrayList<Partido> partidosVisitantes = buscarPartidosVisitante(equipo_id, con);
        for (Partido partidosVisitante : partidosVisitantes) {
            System.out.println(partidosVisitante);     
        }
 
    }
    
    public static ArrayList<Partido> buscarPartidosFecha(LocalDate fecha, Connection con){
        String sql = "SELECT * FROM objetos.partidos WHERE fecha = ?;";
        ArrayList<Partido> partidosFecha = new ArrayList<>();
        Partido p = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(fecha));
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                 p = new Partido(resultado.getInt(1), fecha, EquipoDAO.recuperarEquipo(resultado.getInt(3), con), EquipoDAO.recuperarEquipo(resultado.getInt(4),con));
                 partidosFecha.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return partidosFecha;
       
    }
    
    public static void listarPartidosFecha(LocalDate fecha, Connection con){
        ArrayList<Partido> partidosFecha = buscarPartidosFecha(fecha, con);
        for (Partido partidoFecha : partidosFecha) {
            System.out.println(partidoFecha);     
        }
 
    }
}
