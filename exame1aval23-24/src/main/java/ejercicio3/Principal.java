package ejercicio3;

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
public class Principal {

    public static void main(String[] args) {
        //3a) creamos la conexión
        String url = "jdbc:mysql://localhost:3306/tiendaAD";
        String usu = "root";
        String pass = "";
        String sql = "INSERT INTO cliente(dni, nombre) VALUES(?,?);";
        String sqlBorrar = "DELETE FROM cliente WHERE dni = ?";
        String sqlInfo = "SELECT * FROM cliente WHERE dni = ?";
        String sqlProducto = "SELECT nombre, precio FROM producto";
        try ( Connection conexion = DriverManager.getConnection(url, usu, pass);//creamos la conexión
                  PreparedStatement stmt = conexion.prepareStatement(sql);  PreparedStatement stmtBorrar = conexion.prepareStatement(sqlBorrar);  PreparedStatement stmtInfo = conexion.prepareStatement(sqlInfo);  PreparedStatement stmtProducto = conexion.prepareStatement(sqlProducto);) {
            //comprobamos conexión
            System.out.println("Conexión OK");

            //3b) insertamos cliente:
            stmt.setString(1, "45443344J");
            stmt.setString(2, "Manuel");
            if (stmt.executeUpdate() > 1) {
                System.out.println("Cliente insertado");
            }

            //3b) borramos cliente
            stmtBorrar.setString(1, "45443344J");
            if (stmtBorrar.executeUpdate() > 1) {
                System.out.println("Cliente borrado");
            }

            //3c) mostramos información de un cliente
            String dni = "11111111A";
            stmtInfo.setString(1, dni);
            try ( ResultSet resultado = stmtInfo.executeQuery();) {
                while (resultado.next()) {
                    System.out.println(resultado.getString(1));
                    System.out.println(resultado.getString(2));
                }
            }

            //3d) Mostramos nombre y precio de los productos
            try ( ResultSet resultados = stmtProducto.executeQuery();) {
                while (resultados.next()) {
                    System.out.println(resultados.getString(1) + " " + resultados.getDouble(2));
                }
            }

            //3e) Realizamos un nuevo pedido 
            //(a tener en cuenta: un pedido puede tener varios productos).
            realizarPedido(conexion);
            
            //3f) Mostramos los datos del último pedido
            mostrarUltimoPedido(conexion);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void realizarPedido(Connection conexion) {
        try {
            conexion.setAutoCommit(false); //para que espere a que se haga el commit

            //insertamos un nuevo pedido
            String sqlPedido = "INSERT INTO pedido(dniCliente) VALUES('33333333C');";
            //insertamos un nuevo pedido con productos:
            String sqlProductoPedido = "INSERT INTO producto_pedido(idPedido, idProducto, Cantidad) VALUES(3, 3, 1), (3, 1, 2);";

            try ( PreparedStatement stmtPedido = conexion.prepareStatement(sqlPedido);  PreparedStatement stmtProductoPedido = conexion.prepareStatement(sqlProductoPedido);) {
                stmtPedido.executeUpdate();
                stmtProductoPedido.executeUpdate();

                conexion.commit();

            } catch (SQLException ex) {
                conexion.rollback();//por si da un error que no inserte
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void mostrarUltimoPedido(Connection conexion) {
        try {
            conexion.setAutoCommit(false); //para que espere a que se haga el commit
            
            //consultamos el último pedido del cliente 2:
            String sqlUltimoPedido = "SELECT\n"
                    + "    p.id AS idPedido,\n"
                    + "    p.fecha AS fechaPedido,\n"
                    + "    c.dni AS dniCliente,\n"
                    + "    c.nombre AS nombreCliente,\n"
                    + "    GROUP_CONCAT(pr.nombre) AS productos,\n"
                    + "    SUM(pr.precio * pp.cantidad) AS precioTotal\n"
                    + "FROM\n"
                    + "    pedido p\n"
                    + "JOIN cliente c ON p.dniCliente = c.dni\n"
                    + "JOIN producto_pedido pp ON p.id = pp.idPedido\n"
                    + "JOIN producto pr ON pp.idProducto = pr.id\n"
                    + "WHERE\n"
                    + "    p.fecha = (SELECT MAX(fecha) FROM pedido WHERE dniCliente = c.dni)\n"
                    + "    AND c.dni = '22222222B'\n"
                    + "GROUP BY\n"
                    + "    p.id, p.fecha, c.dni, c.nombre;";

            try ( PreparedStatement stmtUltimoPedido = conexion.prepareStatement(sqlUltimoPedido); ) {
                ResultSet resultado = stmtUltimoPedido.executeQuery();
                while (resultado.next()) {
                    System.out.println("idPedido: " + resultado.getInt(1) 
                            + "; fechaPedido: " + resultado.getDate(2) 
                            + "; dniCliente: " + resultado.getString(3) 
                            + "; nombreCliente: " + resultado.getString(4) 
                            + "; productos: " + resultado.getString(5) 
                            + "; precioTotal: " + resultado.getDouble(6));
                }
                conexion.commit();

            } catch (SQLException ex) {
                conexion.rollback();//por si da un error que no inserte
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
