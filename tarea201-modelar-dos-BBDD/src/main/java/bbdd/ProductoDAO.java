package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * clase de las consultas de la tabla productos
 * @author alba_
 */
public class ProductoDAO {
    /**
     * método que crea un producto
     * @param nombre
     * @param precio
     * @param stock
     * @param nombre_categoria
     * @param nif 
     */
    public void crearProducto(String nombre, Double precio, int stock, String nombre_categoria, String nif) {
        if (!buscarProductoNombre(nombre)) {
            return;
        }
        String sqlCategoria = "SELECT id_categoria FROM categorias WHERE nombre_categoria = ?";
        String sqlProveedor = "SELECT id_proveedor FROM proveedores WHERE nif = ?";
        //Declaramos consulta 
        String sqlPos = "INSERT INTO productos (id_proveedor, id_categoria) VALUES (?, ?);";
        String sqlMy = "INSERT INTO productos (nombre_producto, precio, stock) VALUES (?, ?, ?);";
        //Declaramos conexion e a clase para modificar os valores
        try ( Connection conPos = DBPos.getConnection();  Connection conMy = DBMySQL.getConnection();  PreparedStatement stmtPos = conPos.prepareStatement(sqlPos);  PreparedStatement stmtMy = conMy.prepareStatement(sqlMy);  PreparedStatement stmtCat = conPos.prepareStatement(sqlCategoria);  PreparedStatement stmtPro = conPos.prepareStatement(sqlProveedor);) {
            stmtCat.setString(1, nombre_categoria);
            ResultSet resultado = stmtCat.executeQuery();
            int id_categoria = 0;
            if (resultado.next()) {
                id_categoria = resultado.getInt("id_categoria");
            }
            if (id_categoria == 0) {
                System.out.println("Introduzca un nombre de categoría correcto");
                return;
            }

            stmtPro.setString(1, nif);
            ResultSet resultadoPro = stmtPro.executeQuery();
            int id_proveedor = 0;
            if (resultadoPro.next()) {
                id_proveedor = resultadoPro.getInt("id_proveedor");
            }
            if (id_proveedor == 0) {
                System.out.println("Introduzca un nombre de proveedor correcto");
                return;
            }

            //agregamos los valores
            stmtPos.setInt(1, id_proveedor);
            stmtPos.setInt(2, id_categoria);
            stmtMy.setString(1, nombre);
            stmtMy.setFloat(2, precio.floatValue());
            stmtMy.setInt(3, stock);
            int introducidosMy = stmtMy.executeUpdate();
            int introducidosPos = stmtPos.executeUpdate();
            if (introducidosMy > 0 && introducidosPos > 0) {
                System.out.println("Se ha creado correctamente el producto");
            } else {
                System.out.println("No se ha podido introducir el producto");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * métofo que elimina un producto por un nombre dado
     * @param nombre 
     */
    public void eliminarProductoPorNombre(String nombre) {
        String sql = "DELETE FROM productos WHERE id_producto=?";
        String sqlProducto = "SELECT id_producto FROM productos WHERE nombre_producto=?";
        int idPro;
        try ( Connection con = DBMySQL.getConnection();  Connection conPos = DBPos.getConnection();  PreparedStatement stmtPro = con.prepareStatement(sqlProducto);  PreparedStatement stmtMy = con.prepareStatement(sql);  PreparedStatement stmtPos = conPos.prepareStatement(sql);) {
            stmtPro.setString(1, nombre);
            ResultSet resultado = stmtPro.executeQuery();
            if (resultado.next()) {
                idPro = resultado.getInt("id_producto");
            } else {
                System.out.println("Producto no encontrado");
                resultado.close();
                return;
            }

            stmtMy.setInt(1, idPro);
            int eliminadoMy = stmtMy.executeUpdate();
            if (eliminadoMy > 0) {
                System.out.println("Se ha eliminado correctamente el producto de la base de datos de MySQL");
            } else {
                System.out.println("No se ha podido eliminar el producto de la base de datos de MySQL");
            }

            stmtPos.setInt(1, idPro);
            int eliminadoPos = stmtPos.executeUpdate();
            if (eliminadoPos > 0) {
                System.out.println("Se ha eliminado correctamente el producto de la base de datos de PosgreSQL");
            } else {
                System.out.println("No se ha podido eliminar el producto de la base de datos de PosgreSQL");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * método que lista los productos con bajo stock (menos de X unidades disponibles) 
     * @param stock 
     */
    public void listarProductosBajoStock(int stock) {
        String sqlStock = "SELECT * FROM productos WHERE stock < ?";
        try ( Connection con = DBMySQL.getConnection();  PreparedStatement stmt = con.prepareStatement(sqlStock);) {
            stmt.setInt(1, stock);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                System.out.println("El producto " + resultado.getString("nombre_producto") + " tiene " + resultado.getInt("stock") + " unidades en stock.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * método que busca un producto por su nombre
     * @param nombre
     * @return 
     */
    private boolean buscarProductoNombre(String nombre) {
        String sqlBuscar = "SELECT * FROM productos WHERE nombre_producto=?";

        try ( Connection con = DBMySQL.getConnection();  PreparedStatement stmt = con.prepareStatement(sqlBuscar);) {
            stmt.setString(1, nombre);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                System.out.println("El nombre del producto ya existe");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return true;
    }
   
    public void obtenerCantidadProductosEnCadaAlmacen(){
        String sql = "SELECT nombre_almacen, SUM(cantidad) AS \"Total productos disponibles\" FROM almacenes INNER JOIN almacenes_productos ON almacenes.id_almacen = almacenes_productos.id_almacen GROUP BY almacenes.id_almacen";
        try ( Connection conPos = DBPos.getConnection();  PreparedStatement stmtPos = conPos.prepareStatement(sql);) {       
            ResultSet resultadoPos = stmtPos.executeQuery();
            while (resultadoPos.next()) {
                System.out.println("El almacén " + resultadoPos.getString(1) + " tiene " + resultadoPos.getInt(2) + " productos en total."); 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  
    }
    
    /**
     * método que lista todos los productos con su categoría y proveedor
     */
    public void listarTodosProductosConCategoriaYProveedor() {
        String sqlPos = "SELECT  productos.id_producto, proveedores.nombre_proveedor AS \"nombre proveedor\", proveedores.nif, (proveedores.contacto).telefono, (proveedores.contacto).email, categorias.nombre_categoria FROM productos INNER JOIN categorias ON productos.id_categoria = categorias.id_categoria INNER JOIN proveedores ON productos.id_proveedor = proveedores.id_proveedor";
        String sqlMy = "SELECT nombre_producto, precio, stock FROM productos";
        try ( Connection conPos = DBPos.getConnection();  Connection conMy = DBMySQL.getConnection();  PreparedStatement stmtPos = conPos.prepareStatement(sqlPos);PreparedStatement stmtMy = conMy.prepareStatement(sqlMy);) {       
            ResultSet resultadoPos = stmtPos.executeQuery();
            ResultSet resultadoMy = stmtMy.executeQuery();
            while (resultadoPos.next() && resultadoMy.next()) {
                System.out.println("El producto " + resultadoMy.getString(1) + " con id " + resultadoPos.getInt(1) + ", precio " + resultadoMy.getFloat(2) + " y stock " + resultadoMy.getInt(3) + " tiene de proveedor a " + resultadoPos.getString(2) + ", con nif " + resultadoPos.getString(3) + ", teléfono " + resultadoPos.getString(4) + " e email " + resultadoPos.getString(5) + " y su categoría es " + resultadoPos.getString(6));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
