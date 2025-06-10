# TAREA MONGODB Y XML

## Principal.java
```java

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.examples.api.BaseXClient;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.bson.Document;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alba_
 */
public class Principal {

    static Scanner sc = new Scanner(System.in);
    static String bbdd = "db:open('videojuegos')";
    static int opcion;
    static MongoClient mongoClient;
    private static MongoDatabase database = null;
    private static MongoCollection<Document> coleccionVideojuegos;
    private static MongoCollection<Document> coleccionCarrito;
    private static MongoCollection<Document> coleccionUsuarios;
    private static MongoCollection<Document> coleccionCompras;
    static String mailLog = null;

    public static void main(String[] args) {

        mongoClient = MongoClients.create("mongodb://localhost:27017");

        //MongoDatabase database;
        do {
            try ( BaseXClient sesion = new BaseXClient("localhost", 1984, "admin", "admin")) {
                System.out.println("-------MENÚ------");
                System.out.println("CONSULTAS BASE DE DATOS XML");
                System.out.println("1. Modificar valor de un elemento según su ID");
                System.out.println("2. Eliminar un videojuego según su ID");
                System.out.println("3. Obtener todos los videojuegos ordenados por plataforma y título");
                System.out.println("4. Listar videojuegos con una edad mínima recomendada menor o igual a X años");
                System.out.println("5. Mostrar el videojuego más barato para cada plataforma");
                System.out.println("6. Mostrar videojuegos cuyo descripción incluye una subcadena");
                System.out.println("7. Mostrar cantidad total de videojuegos por plataforma y porcentaje");
                System.out.println("8. Calcular el costo total de comprar todos los videojuegos disponibles");

                System.out.println("\nCONSULTAS BASE DE DATOS MONGODB");
                System.out.println("9. Crear un nuevo usuario");
                System.out.println("10. Identificar usuario según el email");
                System.out.println("11. Borrar un usuario");
                System.out.println("12. Modificar un campo de información del usuario");
                System.out.println("13. Añadir videojuegos al carrito del usuario");
                System.out.println("14. Mostrar el carrito del usuario");
                System.out.println("15. Comprar el carrito del usuario");
                System.out.println("16. Mostrar las compras del usuario");
                System.out.println("17. Calcular el coste de cada carrito (todos los usuarios)");
                System.out.println("18. Calcular el total gastado por cada usuario en compras");

                System.out.println("19. Salir");

                System.out.print("\nSeleccione una opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        modificarElementoXML();
                        break;
                    case 2:
                        eliminarVideojuegoXML();
                        break;
                    case 3:
                        obtenerVideojuegosOrdenados();
                        break;
                    case 4:
                        listarVideojuegosPorEdad();
                        break;
                    case 5:
                        videojuegoMasBaratoPorPlataforma();
                        break;
                    case 6:
                        buscarVideojuegosPorDescripcion();
                        break;
                    case 7:
                        cantidadVideojuegosPorPlataforma();
                        break;
                    case 8:
                        precioTotalVideojuegos();
                        break;
                    case 9:
                        crearUsuario();
                        break;
                    case 10:
                        identificarUsuarioByEmail();
                        break;
                    case 11:
                        borrarUsuario();
                        break;
                    case 12:
                        modificarCampoUsuario();
                        break;
                    case 13:
                        anhadirVideojuegosCarrito();
                        break;
                    case 14:
                        mostrarCarritoUsuario();
                        break;
                    case 15:
                        comprarCarritoUsuarioConfirm();
                        break;
                    case 16:
                        mostrarComprasUsuario();
                        break;
                    case 17:
                        calcularCosteCarritoUsuarios();
                        break;
                    case 18:
                        calcularTotalGastadoUsuarios();
                        break;
                    case 19:
                        System.out.println("Salir");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (opcion != 19);

    }

    /**
     * * ***************************************XML BASEX**********************************************
     */
    
    /**
     * Método que permite devolver todas las consultas
     * @param consulta 
     */
    private static void obtenerDatos(String consulta) {
        //hacemos la conexión y la consulta
        try ( BaseXClient conexionBaseX = new BaseXClient("localhost", 1984, "admin", "admin");) {
            try ( BaseXClient.Query query = conexionBaseX.query(consulta);) {
                System.out.println("Conexión Ok");
                while (query.more()) {
                    System.out.println(query.next()); //imprime el resultado de todas las líneas
                }
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que devuelve todos los videojuegos ordenados por plataforma y por
     * título se mostrarán: id, titulo, precio, disponibilidad,
     * edad_minima_recomendada y plataforma
     */
    private static void obtenerVideojuegosOrdenados() {
        //String bbdd = "db:get('videojuegos')";
        String consulta = "for $v in " + bbdd + "//videojuego "
                + "order by $v/plataforma, $v/titulo "
                + "return <videojuego> "
                + "{$v/id}\n"
                + "{$v/titulo}\n"
                + "{$v/precio}\n"
                + "{$v/disponibilidad}\n"
                + "{$v/edad_minima_recomendada}\n"
                + "{$v/plataforma}\n"
                + "</videojuego>";
        obtenerDatos(consulta);
    }

    /**
     * Método que modifica el valor de un elemento de un XML según un ID.
     */
    private static void modificarElementoXML() {
        System.out.println("Introduce el id del videojuego que quieres modificar:");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el valor del campo que quieres modificar:");
        String valor = sc.nextLine();
        System.out.println("Introduce el nuevo valor:");
        String nuevoValor = sc.nextLine();
        String consulta = "replace value of node " + bbdd + "//videojuego[" + id + "]/" + valor + " with \"" + nuevoValor + "\"";
        obtenerDatos(consulta);
        System.out.println("El videojuego con id: " + id + " ha sido modificado correctamente.");
    }

    /**
     * Método que elimina un videojuego según su ID.
     */
    private static void eliminarVideojuegoXML() {
        System.out.println("Introduce el id del videojuego que quieres eliminar:");
        int id = Integer.parseInt(sc.nextLine());
        String consulta = "delete node " + bbdd + "//videojuego[" + id + "]";
        System.out.println("El videojuego con id: " + id + " ha sido eliminado correctamente");
        obtenerDatos(consulta);
    }

    /**
     * Método que lista los videojuegos por una edad_minima_recomendada menor o
     * igual a X años, ordenada según la edad_minima_recomendada. Se mostrará:
     * id, titulo, precio, disponibilidad, edad_minima_recomendada y
     * plataforma).
     */
     private static void listarVideojuegosPorEdad() {
        System.out.println("Introduce la edad mínima recomendada:");
        int edadMinima = Integer.parseInt(sc.nextLine());
        String consulta = "for $v in " + bbdd + "//videojuego"
                + " where $v/edad_minima_recomendada <= " + edadMinima
                + " order by xs:integer($v/edad_minima_recomendada)"
                + " return <videojuego>\n"
                + "{$v/id}\n"
                + "{$v/titulo}\n"
                + "{$v/precio}\n"
                + "{$v/disponibilidad}\n"
                + "{$v/edad_minima_recomendada}\n"
                + "{$v/plataforma}\n"
                + "</videojuego>";
        obtenerDatos(consulta);
    }

    /**
     * Método que muestra la plataforma, el titulo y el precio del videojuego
     * más barato para cada plataforma.
     */
    private static void videojuegoMasBaratoPorPlataforma() {
        String consulta = "for $p in distinct-values(" + bbdd + "//videojuego/plataforma)\n"
                + "let $min := min(" + bbdd + "//videojuego[plataforma = $p]/precio)\n"
                + "let $vj := (" + bbdd + "//videojuego[plataforma = $p and precio = $min])[1]\n"
                + "return\n"
                + "<videojuego>\n"
                + "{ $vj/plataforma }\n"
                + "{ $vj/titulo }\n"
                + "{ $vj/precio }\n"
                + "</videojuego>";
        obtenerDatos(consulta);
    }

    /**
     * Método que muestra el titulo y el genero de aquellos videojuegos cuya
     * descripcion incluya una subcadena
     */
    private static void buscarVideojuegosPorDescripcion() {
        System.out.println("Introduce una subcadena de la descripción a buscar:");
        String subcadena = sc.nextLine();
        subcadena = subcadena.toLowerCase(); //pasamos ya a lowercase
        String consulta = "for $v in " + bbdd + "//videojuego\n"
                + "where contains(lower-case($v/descripcion), \"" + subcadena + "\")\n"
                + "order by $v/genero\n"
                + "return <videojuego>\n"
                + "{$v/titulo}\n"
                + "{$v/genero}\n"
                + "</videojuego>";
        obtenerDatos(consulta);
    }

    /**
     * Método que muestra la cantidad total de videojuegos para cada plataforma
     * (teniendo en cuenta el elemento disponibilidad) y calcula el porcentaje
     * que representa respecto al total de videojuegos.
     */
    private static void cantidadVideojuegosPorPlataforma() {
        String consulta = "for $p in distinct-values(" + bbdd + "//videojuego/plataforma)\n"
                + "let $total := count(" + bbdd + "//videojuego)\n"
                + "let $cantidadDisp := count(" + bbdd + "//videojuego[disponibilidad > 0 and plataforma = $p])\n"
                + "let $porcentaje := round(($cantidadDisp div $total) * 100, 2)\n"
                + "order by $cantidadDisp descending\n"
                + "return <videojuegosDisponibles>{"
                + "<videojuego>\n"
                + "<plataforma>{ $p }</plataforma>\n"
                + "<total>{ $cantidadDisp }</total>\n"
                + "<porcentaje>{ $porcentaje }%</porcentaje>\n"
                + "</videojuego>"
                + "}</videojuegosDisponibles>";
        obtenerDatos(consulta);
    }

    /**
     * Método que muestra el precio que costaría comprar todos los videojuegos
     * disponibles (teniendo en cuenta el precio de cada videojuego y la
     * disponibilidad de cada uno).
     */
    private static void precioTotalVideojuegos() {
        String consulta = "let $total := sum(\n"
                + "  for $v in " + bbdd + "//videojuego\n"
                + "  where $v/disponibilidad > 0\n"
                + "  return $v/precio * $v/disponibilidad\n"
                + ")\n"
                + "return <precio_total>{$total}</precio_total>";
        obtenerDatos(consulta);
    }

    /** * **************************************MONGODB**********************************************/
    
    /**
     * Método que crea la base de datos y las colecciones en MongoDB
     */
    private static void crearBaseDatosyColeccion() {
        if (database == null) {
            database = mongoClient.getDatabase("comercio");
        }
        try {
            coleccionUsuarios = database.getCollection("usuarios");
            coleccionVideojuegos = database.getCollection("videojuegos");
            coleccionCarrito = database.getCollection("carrito");
            coleccionCompras = database.getCollection("compras");
        } catch (Exception e) {
            database.createCollection("usuarios");
            coleccionUsuarios = database.getCollection("usuarios");
            database.createCollection("usuarios");
            coleccionCarrito = database.getCollection("carrito");
            database.createCollection("videojuegos");
            coleccionVideojuegos = database.getCollection("videojuegos");
            database.createCollection("compras");
            coleccionCompras = database.getCollection("compras");
        }
    }

    /**
     * Método que crea un nuevo usuario (no podrá haber email repetidos).
     */
    private static void crearUsuario() {
        if (database == null) {
            crearBaseDatosyColeccion();
        }

        System.out.println("Introduzca el email: ");
        String email = sc.nextLine();

        Document emails = coleccionUsuarios.find(new Document("_id", email)).first();
        if (emails == null) {
            System.out.println("Introduzca el nombre: ");
            String nombre = sc.nextLine();
            System.out.println("Introduzca la edad: ");
            int edad = Integer.parseInt(sc.nextLine());
            System.out.println("Introduzca la dirección: ");
            String direccion = sc.nextLine();
            Document usuInsert = new Document("_id", email)
                    .append("nombre", nombre)
                    .append("email", email)
                    .append("edad", edad)
                    .append("direccion", direccion);
            coleccionUsuarios.insertOne(usuInsert);
        }

    }

    /**
     * Método que identifica un usuario según el email
     */
    private static void identificarUsuarioByEmail() {
        if (database == null) {
            crearBaseDatosyColeccion();
        }

        System.out.println("Introduzca el email: ");
        String email = sc.nextLine();

        Document emails = coleccionUsuarios.find(new Document("_id", email)).first();
        if (emails != null) {
            mailLog = email;
            System.out.println("Usuario identificado");
        } else {
            System.out.println("Usuario no encontrado. Introduzca un email válido");
        }
    }

    /**
     * Método que modifica el valor de un campo de la información del usuario
     */
    private static void modificarCampoUsuario() {
        if (database == null) {
            crearBaseDatosyColeccion();
        }

        if (mailLog == null) {
            System.out.println("Debes loguearte primero");
            return;
        }

        System.out.println("Introduce el campo que quieres actualizar:");
        String campo = sc.nextLine();
        System.out.println("Introduce el valor nuevo para actualizarlo:");
        String valorNuevo = sc.nextLine();

        Document emails = new Document("email", mailLog);
        Document emailsActualizados = new Document("$set", new Document(campo, valorNuevo));
        UpdateResult urs = coleccionUsuarios.updateOne(emails, emailsActualizados);
        if (urs.getMatchedCount() > 0) {
            System.out.println("Actualizacion correcta. " + urs.getMatchedCount() + " usuarios actualizados.");
            if (campo.equals("email")) {
                mailLog = valorNuevo;
            }
        } else {
            System.out.println("No se ha actualizado ningún usuario");
        }
    }

    /**
     * Método que permite añadir videojuegos al carrito del usuario, cuya
     * edad_minima_recomendada sea inferior o igual a la del usuario actual. Hay
     * que estar logueado
     */
    private static void anhadirVideojuegosCarrito() {
        if (database == null) {
            crearBaseDatosyColeccion();
        }

        if (mailLog == null) {
            System.out.println("Tienes que estar logueado");
            return;
        }

        //recogemos todos los datos
        FindIterable<Document> videojuegos = coleccionVideojuegos.find();
        Document usuarioLogueado = coleccionUsuarios.find(new Document("email", mailLog)).first();

        for (Document videojuego : videojuegos) { //recorremos todos
            //comparamos las edades
            if (videojuego.getInteger("edad_minima_recomendada") <= usuarioLogueado.getInteger("edad")) {
                System.out.println("Los videojuegos recomendados para tu edad son: ");
                System.out.println("id: " + videojuego.get("id") + ", videojuego: " + videojuego.get("titulo") + ", precio: " + videojuego.get("precio"));
            }
        }

        int idIntroducido;

        do {
            System.out.println("Introduzca el id del videojuego a agregar (0 para salir): ");
            idIntroducido = Integer.parseInt(sc.nextLine());
            if (idIntroducido != 0) {
                System.out.println("Introduce la cantidad:");
                int cantidad = Integer.parseInt(sc.nextLine());
                // Filtramos por usuario_id
                Document filtro = new Document("usuario_id", mailLog);
                // hacemos push para que lo introduzca
                Document actualizacion = new Document("$push", new Document("videojuegos",
                        new Document("id", idIntroducido).append("cantidad", cantidad)));

                // Opciones de actualización: creamos el documento si no existe.
                UpdateOptions opciones = new UpdateOptions().upsert(true);
                coleccionCarrito.updateOne(filtro, actualizacion, opciones);

            }
        } while (idIntroducido != 0);

    }

    /**
     * Método que muestra los datos del carrito del usuario y el coste total.
     */
    private static void mostrarCarritoUsuario() {
        if (database == null) {
            crearBaseDatosyColeccion();
        }

        if (mailLog == null) {
            System.out.println("Primero tienes que loguearte.");
            return;
        }

        // Buscamos el carrito del usuario logueado
        Document carrito = coleccionCarrito.find(new Document("usuario_id", mailLog)).first();
        if (carrito == null) {
            System.out.println("El carrito está vacío.");
            return;
        }

        // Obtenemos la lista de videojuegos del carrito
        List<Document> juegosCarrito = carrito.getList("videojuegos", Document.class);
        if (juegosCarrito == null || juegosCarrito.isEmpty()) {
            System.out.println("No hay videojuegos en el carrito.");
            return;
        }

        for (Document juego : juegosCarrito) {
            int id = juego.getInteger("id");
            int cantidad = juego.getInteger("cantidad");

            // Busca el videojuego en la colección de videojuegos
            Document videojuego = coleccionVideojuegos.find(new Document("id", id)).first();
            if (videojuego != null) {
                String titulo = videojuego.getString("titulo");
                double precio = videojuego.getDouble("precio");
                System.out.println("Id: " + id + ", Título: " + titulo + ", Precio: " + precio + ", Cantidad: " + cantidad);
            } else {
                System.out.println("El videojuego con id " + id + " no se encuentra en la colección.");
            }
        }

    }

    /**
     * Método que borra un usuario
     */
    private static void borrarUsuario() {
        if (database == null) {
            crearBaseDatosyColeccion();
        }

        if (mailLog == null) {
            System.out.println("Debes loguearte primero.");
            return;
        }
        String email = mailLog;

        Document usuExist = coleccionUsuarios.find(new Document("email", email)).first();
        if (usuExist != null) {
            coleccionUsuarios.deleteOne(new Document("email", email));
            System.out.println("Se ha borrado el usuario con email: " + email);
            mailLog = null;
        } else {
            System.out.println("El email introducido no existe.");
        }
    }

    /**
     * Método que permite comprar el carrito del usuario. Se mostrará el
     * contenido del carrito junto con una orden de confirmación.
     */
    private static void comprarCarritoUsuarioConfirm() {
        if (database == null) {
            crearBaseDatosyColeccion();
        }

        if (mailLog == null) {
            System.out.println("Debes loguearte primero.");
            return;
        }

        // Busca el contenido carrito del usuario logueado
        Document carrito = coleccionCarrito.find(new Document("usuario_id", mailLog)).first();
        if (carrito == null || !carrito.containsKey("videojuegos")) {
            System.out.println("Tu carrito está vacío.");
            return;
        }

        // Obtenemos la lista de videojuegos que hay en el carrito
        List<Document> juegosCarrito = carrito.getList("videojuegos", Document.class);
        if (juegosCarrito.isEmpty()) {
            System.out.println("No hay videojuegos en el carrito.");
            return;
        }

        double total = 0;
        //Mostramos el contenido del carrito
        System.out.println("\nContenido del carrito:");
        for (Document juego : juegosCarrito) {
            int id = juego.getInteger("id");
            int cantidad = juego.getInteger("cantidad");

            // Buscamos el videojuego en la colección de videojuegos
            Document videojuego = coleccionVideojuegos.find(new Document("id", id)).first();
            if (videojuego != null) {
                String titulo = videojuego.getString("titulo");
                double precio = videojuego.getDouble("precio");
                double subtotal = precio * cantidad;
                total += subtotal;
                System.out.println("Id: " + id + ", Título: " + titulo + ", Precio: " + precio + "€, Cantidad: " + cantidad + ", Subtotal: " + subtotal + "€");
            } else {
                System.out.println("El videojuego con id " + id + " no se ha encuentrado.");
            }
        }
        System.out.println("Total a pagar: " + total + "€");

        System.out.println("¿Quieres confirmar la compra? (S/N)");
        String confirmacion = sc.nextLine().toUpperCase(); // Aseguramos por si pone en minúsculas o mayúsculas

        if (confirmacion.equals("S")) {
            // Creamos la nueva compra
            Document compra = new Document("usuario_id", mailLog)
                    .append("videojuegos", juegosCarrito)
                    .append("total", total)
                    .append("fecha", new Date());

            coleccionCompras.insertOne(compra);

            // Vaciamos el carrito del usuario al realizarse la compra
            coleccionCarrito.deleteOne(new Document("usuario_id", mailLog));

            System.out.println("Compra fue realizada con éxito.");
        } else {
            System.out.println("Compra ha sido cancelada.");
        }
    }

    /**
     * Método que muestra las compras del usuario, incluyendo la información de
     * la fecha de cada compra.
     */
    private static void mostrarComprasUsuario() {
        if (database == null) {
            crearBaseDatosyColeccion();
        }

        if (mailLog == null) {
            System.out.println("Debes loguearte primero.");
            return;
        }

        // Buscamos las compras del usuario en compras
        FindIterable<Document> compras = coleccionCompras.find(new Document("usuario_id", mailLog));

        boolean hayCompras = false;

        for (Document compra : compras) {
            hayCompras = true;
            Date fechaCompra = compra.getDate("fecha");
            List<Document> videojuegosComprados = compra.getList("videojuegos", Document.class);

            System.out.println("\nFecha de compra: " + fechaCompra);
            System.out.println("Videojuegos comprados:");

            for (Document juego : videojuegosComprados) {
                int id = juego.getInteger("id");
                int cantidad = juego.getInteger("cantidad");

                // Buscamos la información en la colección de videojuegos
                Document videojuego = coleccionVideojuegos.find(new Document("id", id)).first();
                if (videojuego != null) {
                    String titulo = videojuego.getString("titulo");
                    double precio = videojuego.getDouble("precio");
                    System.out.println("Título: " + titulo + ", Precio: " + precio + ", Cantidad: " + cantidad);
                } else {
                    System.out.println("El videojuego con id  " + id + " no ha sido encontrado.");
                }
            }
        }

        // si no hay compras
        if (!hayCompras) {
            System.out.println("No tienes compras registradas.");
        }
    }

    /**
     * Método que permite calcular el coste de cada carrito y listar los
     * resultados ordenados por el total de forma descendente. Teniendo en
     * cuenta todos los usuarios,
     */
    private static void calcularCosteCarritoUsuarios() {
        if (database == null) {
            crearBaseDatosyColeccion();
        }

        // Obtenemos todas las compras
        FindIterable<Document> compras = coleccionCompras.find();

        List<Document> listaResultados = new ArrayList<>();

        for (Document compra : compras) {
            String usuarioId = compra.getString("usuario_id");
            Date fechaCompra = compra.getDate("fecha");
            List<Document> videojuegosComprados = compra.getList("videojuegos", Document.class);

            double totalCarrito = 0;

            for (Document videojuego : videojuegosComprados) {
                int id = videojuego.getInteger("id");
                int cantidad = videojuego.getInteger("cantidad");

                Document videojuegos = coleccionVideojuegos.find(new Document("id", id)).first();
                if (videojuegos != null) {
                    double precio = videojuegos.getDouble("precio");
                    totalCarrito += precio * cantidad;
                }
            }

            // Añadimos el resultado
            Document resultado = new Document("usuario_id", usuarioId)
                    .append("fecha", fechaCompra)
                    .append("total", totalCarrito);
            listaResultados.add(resultado);
        }

        // Ordenamos la lista
        listaResultados.sort((a, b) -> Double.compare(b.getDouble("total"), a.getDouble("total")));

        // Imprimimos los resultados
        System.out.println("\nCoste de cada carrito ordenado por el total (descendente):");
        for (Document res : listaResultados) {
            System.out.println("Usuario: " + res.getString("usuario_id")
                    + " | Fecha: " + res.getDate("fecha")
                    + " | Total: " + String.format("%.2f", res.getDouble("total")));
        }
    }

    /**
     * Método que calcula el total gastado por cada usuario en todas sus compras
     * y listar los resultados ordenados por el total de forma ascendente.
     * Teniendo en cuenta todos los usuarios.
     */
    private static void calcularTotalGastadoUsuarios() {
        if (database == null) {
            crearBaseDatosyColeccion();
        }

        Map<String, Double> totalGastadoPorUsuario = new HashMap<>();
        FindIterable<Document> compras = coleccionCompras.find();

        for (Document compra : compras) {
            String usuarioId = compra.getString("usuario_id");
            List<Document> videojuegosComprados = compra.getList("videojuegos", Document.class);

            double totalCompra = 0;

            for (Document juego : videojuegosComprados) {
                int id = juego.getInteger("id");
                int cantidad = juego.getInteger("cantidad");

                Document videojuego = coleccionVideojuegos.find(new Document("id", id)).first();
                if (videojuego != null) {
                    double precio = videojuego.getDouble("precio");
                    totalCompra += precio * cantidad;
                }
            }
            totalGastadoPorUsuario.put(usuarioId, totalGastadoPorUsuario.getOrDefault(usuarioId, 0.0) + totalCompra);
        }

        // Convertimos a list y ordenamos en orden ascendente por el total gastado
        List<Map.Entry<String, Double>> listaOrdenada = new ArrayList<>(totalGastadoPorUsuario.entrySet());
        listaOrdenada.sort(Map.Entry.comparingByValue());

        // Imprimimos los resultados
        System.out.println("\nTotal gastado por cada usuario ordenado ascendentemente:");
        for (Map.Entry<String, Double> entry : listaOrdenada) {
            System.out.println("Usuario: " + entry.getKey() + " | Total gastado: " + String.format("%.2f", entry.getValue()));
        }
    }

}
```

## db
1. **``Carrito.java``**

    ```java
    package db;

    /**
     *
     * @author alba_
     */
    public class Carrito {
    private  String id;
    private String usuarioId;

        public Carrito() {
        }
    }
    ```

2. **``Compra.java``**

    ```java
    package db;

    /**
     *
     * @author alba_
     */
    public class Compra {
        
    }
    ```

3. **``Usuario.java``**

    ```java

    package db;

    /**
     *
     * @author alba_
     */
    public class Usuario {
        private String id;
        private String nombre;
        private String email;
        private int edad;
        private String direccion;

        public Usuario() {
        }

        public Usuario(String id, String nombre, int edad, String direccion ) {
            this.id = id;
            this.nombre = nombre;
            this.email = id;
            this.edad = edad;
            this.direccion = direccion;
        
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getEdad() {
            return edad;
        }

        public void setEdad(int edad) {
            this.edad = edad;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        @Override
        public String toString() {
            return "Usuario: " + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", edad=" + edad + ", direccion=" + direccion;
        } 
    }
    ```

4. **``Videojuego.java``**

    ```java
    package db;

    /**
     * Clase videojuegos
     * @author alba_
     */
    public class Videojuego {
        private int id;
        private String nombre;
        private int cantidad;
        private double precio;
        

        public Videojuego() {
        }

        public Videojuego(int id, String nombre, int cantidad, double precio) {
            this.id = id;
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.precio = precio;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        @Override
        public String toString() {
            return "Videojuego{" + "id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio + '}';
        }
    }
    ```

## Consultas BaseX
```xq
(: título de los videojuegos :)
for $v in doc("videojuegos")//videojuego
return $v/titulo

(: Modificar el valor de un elemento según un ID :)
replace value of node doc("videojuegos")//videojuego[id='1']/precio with "69.99"

(: Consulta 3: Mostrar el videojuego más barato para cada plataforma :)
for $p in distinct-values(//videojuego/plataforma)
let $min := min(doc("videojuegos")//videojuego[plataforma=$p]/precio)
let $vj := doc("videojuegos")//videojuego[plataforma=$p and precio=$min][1]
return <resultado>{
    <plataforma>{$p}</plataforma>, 
    return <videojuego>{$v/titulo, $v/precio}</videojuego>
}</resultado>

(: Consulta 4: Videojuegos cuya descripción incluya una subcadena (insensible a mayúsculas) :)
for $v in //videojuego[contains(lower-case($v/descripcion), lower-case('subcadena'))]
order by $v/genero
return <videojuego>{$v/titulo, $v/genero}</videojuego>
```