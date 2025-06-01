package examen2eval2025_parte2;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.examples.api.BaseXClient;

public class Ejercicio2 {

    static Scanner sc;
    static String documento = "db:open('universidad')";

    public static void main(String[] args) {
        // Conexión a base X --> ("localhost", 1984, "admin", "abc123")
        try ( BaseXClient conexionBX = new BaseXClient("localhost", 1984, "admin", "admin");) {

            sc = new Scanner(System.in).useDelimiter("\n");
            String menu = "1. Consulta 1.\n2. Consulta 2\n3. Consulta 3\n4. Salir\nIntroduzca una opción";
            int opcion;
            do {
                opcion = pedirInt(menu);
                switch (opcion) {
                    case 1:
                        contarProfesores(conexionBX);
                        break;
                    case 2:
                        sacarMediaProfesores(conexionBX);
                        break;
                    case 3:
                        sacarMediaCursos(conexionBX);
                        break;
                }
            } while (opcion != 4);

        } catch (IOException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * método que imprime todas las consultas
     *
     * @param cliente
     * @param consulta
     */
    private static void imprimirConsultas(BaseXClient cliente, String consulta) {
        try {
            BaseXClient.Query sql = cliente.query(consulta);
            while (sql.more()) { //mientras haya consultas...
                System.out.println(sql.next());
            }
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String pedirString(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                sc.nextLine();
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

    private static void contarProfesores(BaseXClient cliente) {
        String departamento = pedirString("Introduce el departamento: ");
        String consulta = "for $x in " + documento + "//departamento where $x[@nombre=\"" + departamento + "\"] return count($x//profesor)";
        System.out.println("El número de profesores del departamento " + departamento + " es: ");
        imprimirConsultas(cliente, consulta);
        System.out.println("");
    }

    private static void sacarMediaProfesores(BaseXClient cliente) {
        String consulta = "avg(for $p in " + documento + "//facultad[@nombre=\"Ingeniería\"]//profesor \n"
                + "let $h := for $pr in $p\n"
                + "              where $pr/horario[@dia=\"Lunes\"]\n"
                + "              return xs:decimal($pr/@antigüedad)\n"
                + "return $h)";
        
        System.out.println("La media de antigúedad de los profesores de la facultad de Ingeniería que trabaja algún lunes es: ");
        imprimirConsultas(cliente, consulta);
        System.out.println("");
    }

    private static void sacarMediaCursos(BaseXClient cliente) {
        String consulta = "avg(for $x in " + documento +"//facultad\n"
                + "return count($x//curso))";
        System.out.println("El número medio de cursos ofrecidos por cada facultad es: ");
        imprimirConsultas(cliente, consulta);
    }
}
