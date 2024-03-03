package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alba_
 */
public class EquipoDAO {
    public static int insertarEquipo(Equipo equipo, Connection con) {
        //System.out.println("Entras?");
       // String sql = "INSERT INTO objetos.Equipos(equipo_id, equipo_info) VALUES(?,ROW(?,?,ROW(?,?)))";
        String sql = "INSERT INTO objetos.Equipos(equipo_info) VALUES(ROW(?,?,ROW(?,?)))";
        try (PreparedStatement stmt = con.prepareStatement(sql);) {
//            stmt.setInt(1, equipo.getEquipo_id());
            stmt.setString(1, equipo.getNombre());
          
            stmt.setString(2, equipo.getCiudad());    
            stmt.setString(3, equipo.getEntrenador().getNombre());
            stmt.setInt(4, equipo.getEntrenador().getEdad());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1;
    }
    public static int modificarEquipo(Equipo equipo, Connection con) {
        String sql = "UPDATE objetos.Equipos SET equipo_info=(?,?, (?,?)) WHERE equipo_id=?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, equipo.getNombre());
                stmt.setString(2, equipo.getCiudad());
                stmt.setString(3, equipo.getEntrenador().getNombre());
                stmt.setInt(4, equipo.getEntrenador().getEdad());
                stmt.setInt(5, equipo.getEquipo_id());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }    
            return 1;
    }
    
    public static int eliminarEquipo(int equipo_id, Connection con) {
        String sql = "DELETE FROM objetos.Equipos WHERE equipo_id=?";    
        try (PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, equipo_id);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1;
    }
    
    public static Equipo recuperarEquipo(int equipo_id, Connection con)  {
        String sql = "SELECT (equipo_info).nombre, (equipo_info).ciudad, (equipo_info).entrenador.nombre, (equipo_info).entrenador.edad FROM objetos.Equipos WHERE equipo_id=?;";
        Equipo e = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, equipo_id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                //System.out.println(resultado.getString(1) +" "+ resultado.getString(2)+" "+ resultado.getString(3)+" "+ resultado.getInt(4));
                 e = new Equipo(equipo_id, resultado.getString(1), resultado.getString(2), new Persona(resultado.getString(3), resultado.getInt(4)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }
    
    public static void listarEquipo(int equipo_id, Connection con){
        Equipo equipoLista = recuperarEquipo(equipo_id, con);
        System.out.println(equipoLista);
    }
    
    public static ArrayList<Equipo> recuperarEquipos(Connection con)  {
        String sql = "SELECT equipo_id, (equipo_info).nombre, (equipo_info).ciudad, (equipo_info).entrenador.nombre, (equipo_info).entrenador.edad FROM objetos.Equipos;";
        ArrayList<Equipo> equipos = new ArrayList<>();
        Equipo e = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                //System.out.println(resultado.getString(1) +" "+ resultado.getString(2)+" "+ resultado.getString(3)+" "+ resultado.getInt(4));
                 e = new Equipo(resultado.getInt(1), resultado.getString(2), resultado.getString(3), new Persona(resultado.getString(4), resultado.getInt(5)));
                 equipos.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return equipos;
    }
    
    public static void listarEquipos(Connection con){
        ArrayList<Equipo> equiposLista = recuperarEquipos(con);
        for (Equipo equipo : equiposLista) {
            System.out.println(equipo);
        }   
    }
    
    
    
}
