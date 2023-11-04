/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo {
    
    //private final Connection conn;
    private final String usuario = "root";
    private final String clave = "abc123.";
    private final String url ="jdbc:mysql://localhost:3306/empleados";
    public Modelo() throws SQLException{
        //conn = DriverManager.getConnection(this.url, this.usuario,this.clave);
    }
    
    public String obtenerDatosEmpleado(String numeroEmpleado){
        
        String resultadoString = "sda";
//        try {
//            Statement stmt = conn.createStatement();
//            
//            ResultSet rs = stmt.executeQuery("select * from empleado where NSS = " + numeroEmpleado);
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
        return resultadoString;
    }
    
}
