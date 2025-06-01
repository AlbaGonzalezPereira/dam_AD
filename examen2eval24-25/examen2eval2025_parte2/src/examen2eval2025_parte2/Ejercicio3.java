package examen2eval2025_parte2;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.size;
import java.util.Arrays;
import java.util.Scanner;
import org.bson.BsonNull;
import org.bson.Document;
import org.bson.conversions.Bson;

public class Ejercicio3 {

    static Scanner sc;
    static MongoClient mongoClient;
    static MongoCollection<Document> collection;
    static MongoDatabase database;

    public static void main(String[] args) {

        //creamos la conexión
        crearConexion();

        sc = new Scanner(System.in).useDelimiter("\n");
        String menu = "1. Consulta 1.\n2. Consulta 2\n3. Consulta 3\n4. Eliminar autores\n5. Salir\nIntroduzca una opción";
        int opcion;
        do {
            opcion = pedirInt(menu);
            switch (opcion) {
                case 1:
                    crearConsulta1();
                    break;
                case 2:
                    crearConsulta2();
                    break;
                case 3:
                    crearConsulta3();
                    break;
                case 4:
                    eliminarAutores();
                    break;
            }
        } while (opcion != 5);
    }

    //para seleccionar la colección
    private static void seleccionarColeccion(String coleccion) {
        database = mongoClient.getDatabase("biblioteca");
        collection = database.getCollection(coleccion);
    }

    private static String pedirString(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                return sc.nextLine();
            } catch (Exception ignored) {
            }
        }
    }

    private static int pedirInt(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                return sc.nextInt();
            } catch (Exception ignored) {
            }
        }
    }

    private static void crearConexion() {
        //creamos la conexión MongoDB
        mongoClient = new MongoClient(
                new MongoClientURI(
                        "mongodb://localhost:27017/"
                )
        );
    }

    /**
     * método que Lista los autores que han escrito libros del género Ficción
     * histórica y devolver el nombre, la edad y los premios pero no el ID.
     * Solución: Isabel Allende
     */
    private static void crearConsulta1() {
        seleccionarColeccion("autores");

        Bson filter = eq("genero", "Ficción histórica");
        Bson project = and(eq("nombre", 1L), eq("edad", 1L), eq("premios", 1L), eq("_id", 0L));

        FindIterable<Document> resultados = collection.find(filter).projection(project);
        for (Document resultado : resultados) {
            resultado.toString();
            System.out.println(resultado.toString()); //imprimimos los resultados
        }
    }

    /**
     * método que Lista el nombre de los autores mayores de 60 años que han
     * recibido el "Premio Nobel de Literatura". Solución: Gabriel García
     * Márquez
     */
    private static void crearConsulta2() {
        seleccionarColeccion("autores");

        Bson filter = and(Arrays.asList(eq("premios.nombre", "Premio Nobel de Literatura"), gt("edad", 60L)));
        Bson project = and(eq("nombre", 1L), eq("_id", 0L));

        FindIterable<Document> resultados2 = collection.find(filter).projection(project);
        for (Document resultado : resultados2) {
            resultado.toString();
            System.out.println(resultado.toString()); //imprimimos los resultados
        }
    }

    /**
     * método que Crea una agregación que permita obtener la cantidad total de
     * ventas de libros de todos los autores. Solución: {"total_ventas":
     * 324000000}
     */
    private static void crearConsulta3() {
        seleccionarColeccion("autores");

        AggregateIterable<Document> resultados3 = collection.aggregate(Arrays.asList(new Document("$unwind",
                new Document("path", "$libros")
                        .append("preserveNullAndEmptyArrays", true)),
                new Document("$group",
                        new Document("_id",
                                new BsonNull())
                                .append("sumaTotal",
                                        new Document("$sum", "$libros.ventas"))),
                new Document("$project",
                        new Document("_id", 0L)
                                .append("sumaTotal", 1L))));

        for (Document resultado : resultados3) {
            resultado.toString();
            System.out.println(resultado.toString()); //imprimimos los resultados
        }
    }

    /**
     * método que Elimina los autores cuya nacionalidad sea "Japonesa" y que no hayan recibido ningún premio. 
     * Solución: un elemento eliminado
     */
    private static void eliminarAutores() {
        seleccionarColeccion("autores");
        
         Bson filter = and(Arrays.asList(eq("nacionalidad", "Japonesa"), size("premios", 0)));
        collection.deleteMany(filter); //eliminamos todos con las características especificadas
    }
}
