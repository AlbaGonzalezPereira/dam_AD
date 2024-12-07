package Principal;

import bbdd.CategoriaDAO;
import bbdd.DBMySQL;
import bbdd.DBPos;
import bbdd.PedidoDAO;
import bbdd.ProductoDAO;
import bbdd.ProveedorDAO;
import bbdd.UsuarioDAO;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alba_
 */
public class Principal {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            DBPos.open(); //abrimos la conexión
            DBMySQL.open();
            int opcion;

            do {
                System.out.println("           MENÚ          ");
                System.out.println("*************************");
                System.out.println("1. Insertar una nueva categoría");
                System.out.println("2. Crear un nuevo proveedor");
                System.out.println("3. Eliminar un nuevo proveedor");
                System.out.println("4. Crear un nuevo usuario");
                System.out.println("5. Eliminar un usuario");
                System.out.println("6. Crear nuevo producto");
                System.out.println("7. Eliminar un producto por su nombre");
                System.out.println("8. Listar los productos con bajo stock");
                System.out.println("9. Obtener el total de pedidos realizados por cada usuario");
                System.out.println("10. Obtener la cantidad de productos almacenados por cada almacén");
                System.out.println("11. Listar todos los productos con sus respectivas categorías y proveedores");
                System.out.println("12. Obtener todos los Usuarios que han comprado algún producto de una categoria dada");
                System.out.println("Pulsa 0 para Salir");
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 0:
                        System.out.println("Salir");
                        break;
                    case 1:
                        insertarCategoria();
                        break;
                    case 2:
                        crearProveedor();
                        break;
                    case 3:
                        eliminarProveedor();
                        break;
                    case 4:
                        crearUsuario();
                        break;
                    case 5:
                        eliminarUsuario();
                        break;
                    case 6:
                        crearProducto();
                        break;
                    case 7:
                        eliminarProductoByName();
                        break;
                    case 8:
                        listarproductosBajoStock();
                        break;
                    case 9:
                        obtenerTotalPedidosByUser();
                        break;                  
                    case 10:
                        obtenerCantidadProductosAlmacen();
                        break;
                    case 11:
                        listarProductosCategoriaProveedor();
                        break;
                         case 12:
                        listarUsuariosCompras();
                        break;
                    default:
                        System.out.println("Opción inválida");
                        break;
                }
            } while (opcion != 0);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void insertarCategoria() {
        System.out.println("Introduzca la categoria: ");
        String nombreCategoria = sc.nextLine();
        CategoriaDAO categoria = new CategoriaDAO();
        categoria.crearCategoria(nombreCategoria);
    }

    private static void crearProveedor() {
        System.out.println("Introduzca el nombre del proveedor: ");
        String nombreProveedor = sc.nextLine();
        System.out.println("Introduzca el nif: ");
        String nif = sc.nextLine();
        System.out.println("Introduzca el teléfono: ");
        int telefono = Integer.parseInt(sc.nextLine());
        System.out.println("Introduzca el email: ");
        String email = sc.nextLine();
        ProveedorDAO proveedor = new ProveedorDAO();
        proveedor.crearNuevoProveedor(nombreProveedor, nif, telefono, email);
    }

    private static void eliminarProveedor() {
        System.out.println("Ingrese el id del proveedor a eliminar");
        int id = Integer.parseInt(sc.nextLine());
        ProveedorDAO proveedorElim = new ProveedorDAO();
        proveedorElim.eliminarProveedor(id);

    }

    private static void crearUsuario() {
        System.out.println("Introduzca el nombre del usuario: ");
        String nombreUsu = sc.nextLine();
        System.out.println("Introduzca el email: ");
        String email = sc.nextLine();
        System.out.println("Introduzca el año de nacimiento: ");
        int anho = Integer.parseInt(sc.nextLine());
        UsuarioDAO usuario = new UsuarioDAO();
        usuario.crearUsuario(nombreUsu, email, anho);
    }

    private static void eliminarUsuario() {
        System.out.println("Ingrese el id del usuario a eliminar");
        int id = Integer.parseInt(sc.nextLine());
        UsuarioDAO usuarioElim = new UsuarioDAO();
        usuarioElim.eliminarUsuario(id);

    }

    private static void crearProducto() {
        System.out.println("Introduce el nombre del producto");
        String nombre = sc.nextLine();
        System.out.println("Introduce el precio del producto");
        Double precio = Double.parseDouble(sc.nextLine());
        System.out.println("Introduce el stock del producto");
        int stock = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el nombre de la categoria");
        String categoria = sc.nextLine();
        System.out.println("Introduce el nif(Nombre proveedor)");
        String nif = sc.nextLine();
        ProductoDAO producto = new ProductoDAO();
        producto.crearProducto(nombre, precio, stock, categoria, nif);
    }

    private static void eliminarProductoByName() {
        System.out.println("Introduce el nombre del producto: ");
        String nombre = sc.nextLine();
        ProductoDAO productoElimName = new ProductoDAO();
        productoElimName.eliminarProductoPorNombre(nombre);

    }

    private static void listarproductosBajoStock() {
        System.out.println("Introduzca el límite de stock para listar productos con bajo inventario: ");
        int limite = Integer.parseInt(sc.nextLine());
        ProductoDAO productos = new ProductoDAO();
        productos.listarProductosBajoStock(limite);
    }

    private static void obtenerTotalPedidosByUser(){ 
            PedidoDAO totalPedidos = new PedidoDAO();
            totalPedidos.obtenerTotalPedidosUsuarios();
    }

    private static void obtenerCantidadProductosAlmacen() {
        ProductoDAO productos = new ProductoDAO();
        productos.obtenerCantidadProductosEnCadaAlmacen();      
    }

    private static void listarProductosCategoriaProveedor() {
        ProductoDAO productosCateg = new ProductoDAO();
        productosCateg.listarTodosProductosConCategoriaYProveedor();
    }

    private static void listarUsuariosCompras() {
        System.out.println("Introduzca el id de la categoría: ");
        int idCat = Integer.parseInt(sc.nextLine());
        UsuarioDAO usuarios = new UsuarioDAO();
        usuarios.obtenerUsuariosCompraronProductosCategoria(idCat);
    }
    
}
