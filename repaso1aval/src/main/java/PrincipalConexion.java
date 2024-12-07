
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alba_
 */
public class PrincipalConexion {
    public static void main(String[] args) {
        String usu = "root";
        String url = "jdbc:mysql://localhost:3306/alumnos";
        String pass = "";
        try {      
            Connection conexion = DriverManager.getConnection(url, usu, pass);
            //String url = "jdbc:postgresql://localhost:5432/alumnos";
            System.out.println("Conectado");
            insertarAlumno(conexion);
            actualizarAlumno(conexion);
            obtenerResultados(conexion);
            //borrarAlumnoPorId(conexion);
            
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//fin main

    private static void obtenerResultados(Connection conexion) {
        String sql = "SELECT nombre FROM alumno";
        try(PreparedStatement stmt = conexion.prepareCall(sql);) {
            ResultSet resultados = stmt.executeQuery();
            while(resultados.next()){
                System.out.println("Nombre: " + resultados.getString("nombre"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void insertarAlumno(Connection conexion) {
        String sql = "INSERT INTO alumno(nombre, apellidos, edad, email) VALUES(?,?,?,?)";
        try(PreparedStatement stmt = conexion.prepareStatement(sql);) {        
            stmt.setString(1, "Pablo");
            stmt.setString(2, "Rodríguez Martínez");
            stmt.setInt(3, 48);
            stmt.setString(4, null);
            int insertado = stmt.executeUpdate();
            if(insertado > 0){
                System.out.println("Se ha insertado el alumno correctamente.");
            } else {
                System.out.println("No se ha podido insertar el alumno.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void borrarAlumnoPorId(Connection conexion) {
        String sql = "DELETE FROM alumno WHERE id_alumno = ?";
        try(PreparedStatement stmt = conexion.prepareStatement(sql);) {
            stmt.setInt(1, 1);
            int eliminado = stmt.executeUpdate();
            if(eliminado > 0){
                System.out.println("Se ha eliminado correctamente el alumno");
            } else {
                System.out.println("No se ha podido eliminar el alumno");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static void actualizarAlumno(Connection conexion) {
        String sql ="UPDATE alumno SET nombre = ?";
        try(PreparedStatement stmt = conexion.prepareStatement(sql);) {
            stmt.setString(1, "Alba");
            int actualizados = stmt.executeUpdate();
            if(actualizados > 0){
                System.out.println("Se han actualizado correctamente los nombres");
            } else {
                System.out.println("No se han actualizado los nombres");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
