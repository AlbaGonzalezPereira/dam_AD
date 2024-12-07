package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * clase de las consultas a la tabla de usuarios
 * @author alba_
 */
public class UsuarioDAO {

    /**
     * método que crea un usuario
     * @param nombre
     * @param email
     * @param anho_nacimiento 
     */
    public void crearUsuario(String nombre, String email, int anho_nacimiento) {
        //Declaramos consulta 
        String sql = "INSERT INTO usuarios (nombre, email, anho) VALUES (?, ?, ?);";
        //Declaramos conexion e a clase para modificar os valores
        try ( Connection con = DBMySQL.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            //agregamos los valores
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setInt(3, anho_nacimiento);
            int introducidos = stmt.executeUpdate();
            if (introducidos > 0) {
                System.out.println("Se ha creado correctamente el usuario");
            } else {
                System.out.println("No se ha podido introducir el usuario");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * método que elimina un usuario por id dado
     * @param id 
     */
    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario=?";
        try ( Connection con = DBMySQL.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, id);
            int eliminado = stmt.executeUpdate();
            if (eliminado > 0) {
                System.out.println("Se ha eliminado correctamente el usuario");
            } else {
                System.out.println("No se ha podido eliminar el usuario");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Método para obtener todos los usuarios que han comprado algún producto de una categoria dada
     * @param idCategoria 
     */
    public void obtenerUsuariosCompraronProductosCategoria(int idCategoria) {
        String sqlPos = "SELECT id_producto FROM productos WHERE id_categoria = ? GROUP BY id_producto";
        String sqlMy = "SELECT usuarios.nombre FROM usuarios INNER JOIN pedidos ON usuarios.id_usuario = pedidos.id_usuario INNER JOIN pedidos_productos ON pedidos.id_pedido = pedidos_productos.id_pedido WHERE id_producto = ? GROUP BY usuarios.nombre";
        Set<String> nombresUsuarios = new HashSet<>();
        try ( Connection conPos = DBPos.getConnection();  Connection conMy = DBMySQL.getConnection();  PreparedStatement stmtPos = conPos.prepareStatement(sqlPos);  PreparedStatement stmtMy = conMy.prepareStatement(sqlMy);) {
            stmtPos.setInt(1, idCategoria);
            ResultSet resultado = stmtPos.executeQuery();

            //recorremos el resultado y guardamos el nombre en el Set
            while (resultado.next()) {
                stmtMy.setInt(1, resultado.getInt(1));
                ResultSet resultadoMy = stmtMy.executeQuery();
                while (resultadoMy.next()) {
                    String nombre = resultadoMy.getString(1);
                    nombresUsuarios.add(nombre);//añadimos a la colección
                }
            }
            //comprobamos si la colección no está vacía
            if (!nombresUsuarios.isEmpty()) {
                //recorremos
                for (String nombreUsuario : nombresUsuarios) {
                    System.out.println(nombreUsuario);
                }
            } else {
                System.out.println("No hay usuarios que han comprado algún producto con esa categoría");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
