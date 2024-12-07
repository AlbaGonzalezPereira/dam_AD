package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * clase de consultas a la tabla de proveedores
 * @author alba_
 */
public class ProveedorDAO {
    /**
     * método que crea un nuevo proveedor
     * @param nombreProveedor
     * @param nif
     * @param telefono
     * @param email 
     */
    public void crearNuevoProveedor(String nombreProveedor, String nif, int telefono, String email){
        //comprobamos que non existe o nif do proveedor
        if(!buscarProveedorNif(nif)){
            return;
        }
        //Declaramos consulta 
        String sql = "INSERT INTO proveedores (nombre_proveedor, nif, contacto) VALUES (?, ?, ROW(?,?,?));";
        //Declaramos conexion e a clase para modificar os valores
        try ( Connection con = DBPos.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            //agregamos los valores
            stmt.setString(1, nombreProveedor);
            stmt.setString(2, nif);
            stmt.setString(3, null);
            stmt.setInt(4, telefono);
            stmt.setString(5, email);
            int introducidos = stmt.executeUpdate();
            if(introducidos > 0)
                System.out.println("Se ha creado correctamente el proveedor");
            else
                System.out.println("No se ha podido introducir el proveedor");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    }
    
    /**
     * método que elimina un proveedor de un id dado
     * @param id 
     */
    public void eliminarProveedor(int id){
        String sql = "DELETE FROM proveedores WHERE id_proveedor=?";
        try ( Connection con = DBPos.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, id);
            int eliminado = stmt.executeUpdate();
            if(eliminado > 0)
                System.out.println("Se ha eliminado correctamente el proveedor");
            else
                System.out.println("No se ha podido eliminar el proveedor");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  
    
    }
    
    /**
     * método que busca un proveedor por nif
     * @param nif
     * @return 
     */
    public boolean buscarProveedorNif(String nif) {
        String sqlBuscar = "SELECT * FROM proveedores WHERE nif=?";

        try ( Connection con = DBPos.getConnection();  PreparedStatement stmt = con.prepareStatement(sqlBuscar);) {
            stmt.setString(1, nif);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                System.out.println("El nif " + nif + " ya existe. No se puede duplicar");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return true;
    }
    
}
