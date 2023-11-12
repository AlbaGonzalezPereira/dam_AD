package modelo;

import exception.CodeNotFoundException;
import exception.HireNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Modelo {

//    private final Connection conn;
//    private final String usuario = "root";
//    private final String clave = "";
//    private final String url ="jdbc:mariadb://localhost/tarea301?allowPublicKeyRetrieval=true&useSSL=false;";
    public Modelo() throws SQLException {
//        conn = DriverManager.getConnection(this.url, this.usuario,this.clave);
        try {
            DB.open();
            System.out.println("ok");
        } catch (SQLException ex) {
            System.out.println("Error en la conexión a la base de datos: " + ex.getMessage());
        }
    }

    public ArrayList<Socio> obtenerDatosSocios() {

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

    public ArrayList<Libro> obtenerLibrosDisponibles() {

        try {
            return LibroDAO.cargarLibrosDisponibles();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Alquiler> obtenerLibrosAlquilados() {
        try {
            return AlquilerDAO.cargarLibrosAlquilados();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public int anhadirLibroAlquilado(String dni, String codigo) throws HireNotFoundException {
        try {
            return LibroDAO.alquilarLibro(codigo, dni);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    public int devolverLibroAlquilado(String codigo) throws CodeNotFoundException {
        try {
            return AlquilerDAO.devolverLibro(codigo);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }
    
    public ArrayList<Alquiler> obtenerHistoricosAlquiler() {
        try {
            return AlquilerDAO.obtenerHistorico();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
