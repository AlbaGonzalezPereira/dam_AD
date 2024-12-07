package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alba_
 */
public class PedidoDAO {
    public void obtenerTotalPedidosUsuarios(){
        String sqlTotal = "SELECT usuarios.nombre, COUNT(*) AS \"CANTIDAD PEDIDOS\" FROM pedidos INNER JOIN usuarios ON (pedidos.id_usuario = usuarios.id_usuario) GROUP BY usuarios.id_usuario";        
        try(Connection con = DBMySQL.getConnection();PreparedStatement stmt = con.prepareStatement(sqlTotal);) {
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()){
                System.out.println("El usuario " + resultado.getString("nombre") + " tiene " + resultado.getInt(2) + " pedidos");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
