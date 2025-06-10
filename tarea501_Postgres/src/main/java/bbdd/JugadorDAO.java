package bbdd;

import static bbdd.EquipoDAO.recuperarEquipos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author alba_
 */
public class JugadorDAO {

    public static int insertarJugador(Jugadores jugador, Connection con) {
        String sql = "INSERT INTO objetos.Jugadores(datos_personales, jugador_info, equipo_id) VALUES(ROW(?,?),ROW(?,?,?),null)";
        try ( PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setString(1, jugador.getDatos_personales().getNombre());
            stmt.setInt(2, jugador.getDatos_personales().getEdad());
            stmt.setInt(3, jugador.getJugador_info().getDorsal());
            stmt.setString(4, jugador.getJugador_info().getPosicion());
            stmt.setFloat(5, jugador.getJugador_info().getAltura());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1;
    }

    public static int modificarJugador(Jugadores jugador, Connection con) {
        String sql = "UPDATE objetos.Jugadores SET datos_personales = ROW(?, ?), jugador_info = ROW(?, ?, ?), equipo_id = ? WHERE jugador_id = ?";
        try ( PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setString(1, jugador.getDatos_personales().getNombre());
            stmt.setInt(2, jugador.getDatos_personales().getEdad());
            stmt.setInt(3, jugador.getJugador_info().getDorsal());
            stmt.setString(4, jugador.getJugador_info().getPosicion());
            stmt.setFloat(5, jugador.getJugador_info().getAltura());

            if (jugador.getEquipo() != null) {
                stmt.setInt(6, jugador.getEquipo().getEquipo_id()); // Nuevo equipo al que pertenece el jugador
            } else {
                stmt.setNull(6, Types.INTEGER); // Equipo nulo
            }

            stmt.setInt(7, jugador.getJugador_id()); // Jugador que se está modificando
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0; // Retornar 0 en caso de error
        }
        return 1; // Retornar 1 si la modificación se realiza con éxito
    }

    public static int eliminarJugador(int jugador_id, Connection con) {
        String sql = "DELETE FROM objetos.Jugadores WHERE jugador_id=?";
        try ( PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, jugador_id);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1;
    }
    
    public static int inscribirJugador(int jugador_id, int equipo_id, Connection con){
        String sql = "UPDATE objetos.Jugadores SET equipo_id = ? WHERE jugador_id = ?";
        try ( PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, equipo_id);
            stmt.setInt(2, jugador_id);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return -1;//en caso de no insertar
    }
    
    public static int desinscribirJugador(int jugador_id, Connection con){
        String sql = "UPDATE objetos.Jugadores SET equipo_id = null WHERE jugador_id = ?";
        try ( PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, jugador_id);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return -1;//en caso de no insertar
    }
    
    public static Jugadores recuperarJugador(int jugador_id, Connection con)  {
        String sql = "SELECT (datos_personales).nombre, (datos_personales).edad, (jugador_info).dorsal, (jugador_info).posicion, (jugador_info).altura FROM objetos.Jugadores WHERE jugador_id=?;";
        Jugadores j = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, jugador_id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                 j = new Jugadores(jugador_id,new Persona(resultado.getString(1), resultado.getInt(2)), new Jugador(resultado.getInt(3), resultado.getString(4), resultado.getInt(5)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return j;
    }
    
    public static void listarJugador(int jugador_id, Connection con){
        Jugadores jugadorLista = recuperarJugador(jugador_id, con);
        System.out.println(jugadorLista);
    }
    
    public static Jugadores recuperarJugador(String nombre, Connection con)  {
        String sql = "SELECT (datos_personales).nombre, (datos_personales).edad, (jugador_info).dorsal, (jugador_info).posicion, (jugador_info).altura FROM objetos.Jugadores WHERE (datos_personales).nombre=?;";
        Jugadores j = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                 j = new Jugadores(new Persona(resultado.getString(1), resultado.getInt(2)), new Jugador(resultado.getInt(3), resultado.getString(4), resultado.getInt(5)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return j;
    }
    
    public static void listarJugador(String nombre, Connection con){
        Jugadores jugadorLista = recuperarJugador(nombre, con);
        System.out.println(jugadorLista);
    }
    
    public static ArrayList<Jugadores> recuperarJugadoresPosicion(String posicion, Connection con){
        String sql = "SELECT jugador_id, (datos_personales).nombre, (datos_personales).edad, (jugador_info).dorsal, (jugador_info).posicion, (jugador_info).altura FROM objetos.jugadores where (jugador_info).posicion =?;";
        ArrayList<Jugadores> jugadoresPosicion = new ArrayList<>();
        Jugadores j = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, posicion);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                 j = new Jugadores(resultado.getInt(1),new Persona(resultado.getString(2), resultado.getInt(3)), new Jugador(resultado.getInt(4), resultado.getString(5), resultado.getInt(6)));
                 jugadoresPosicion.add(j);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jugadoresPosicion;
        
    }
    
    public static void listarJugadoresPosicion(String posicion, Connection con){
        ArrayList<Jugadores> posiciones = recuperarJugadoresPosicion(posicion, con);
        for (Jugadores pos : posiciones) {
            System.out.println(pos);        
        }
    }
    
    public static ArrayList<Jugadores> recuperarJugadoresDorsal(int dorsal, Connection con){
        String sql = "SELECT jugador_id, (datos_personales).nombre, (datos_personales).edad, (jugador_info).dorsal, (jugador_info).posicion, (jugador_info).altura FROM objetos.jugadores where (jugador_info).dorsal = ?;";
        ArrayList<Jugadores> jugadoresDorsal = new ArrayList<>();
        Jugadores j = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, dorsal);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                 j = new Jugadores(resultado.getInt(1),new Persona(resultado.getString(2), resultado.getInt(3)), new Jugador(resultado.getInt(4), resultado.getString(5), resultado.getInt(6)));
                 jugadoresDorsal.add(j);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jugadoresDorsal;
        
    }
    
    public static void listarJugadoresDorsal(int dorsal, Connection con){
        ArrayList<Jugadores> jugadoresDorsal = recuperarJugadoresDorsal(dorsal, con);
        for (Jugadores jugadorDorsal : jugadoresDorsal) {
            System.out.println(jugadorDorsal);        
        }
    }
}
