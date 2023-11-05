package modelo;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Modelo {
    
//    private final Connection conn;
//    private final String usuario = "root";
//    private final String clave = "";
//    private final String url ="jdbc:mariadb://localhost/tarea301?allowPublicKeyRetrieval=true&useSSL=false;";
    public Modelo() throws SQLException{
//        conn = DriverManager.getConnection(this.url, this.usuario,this.clave);
 try {
            DB.open();
            System.out.println("ok");
        } catch (SQLException ex) {
            System.out.println("Error en la conexión a la base de datos: " + ex.getMessage());
        }
    }
    
    public ArrayList<Socio> obtenerDatosSocios(){
        
        try {
            return SocioDAO.cargarSocios();
            
//        try {
//            Statement stmt = conn.createStatement();
//            
//            ResultSet rs = stmt.executeQuery("select * from socios");
//            while(rs.next()){
//                resultadoString = "NSS: " + rs.getInt("NSS") +
//                                "\nNombre: " + rs.getString("Nombre") +
//                                "\nApellido 1: " + rs.getString("Apel1") + 
//                                 "\nApellido 2 : " + rs.getString("Apel2") +
//                                 "\nSexo: " + rs.getString("Sexo") +
//                                 "\nDirección: " + rs.getString("Dirección") +
//                                 "\nFecha nacimiento: " + rs.getString("Fechanac") +
//                                 "\nSalario: " + rs.getString("Salario") +
//                                 "\nNúm. departamento: " + rs.getString("Numdept") +
//                                 "\nNSSsup: " + rs.getString("NSSsup");
//            }
//            conn.close();
//        } catch (SQLException ex) {
//            return "Error: " + ex.toString();
//        }
        } catch (SQLException ex) {
            //Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
}
