package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author alba_
 */
public class CategoriaDAO {
    public void crearCategoria( String nombreCategoria){
        //Declaramos consulta 
        String sql = "INSERT INTO categorias (nombre_categoria) VALUES (?);";
        //Declaramos conexion e a clase para modificar os valores
        try ( Connection con = DBPos.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            //agregamos los valores
            stmt.setString(1, nombreCategoria);
            int introducidos = stmt.executeUpdate();
            if(introducidos > 0)
                System.out.println("Se ha creado correctamente la categoría");
            else
                System.out.println("No se ha podido introducir la categoría");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    }
    
}
