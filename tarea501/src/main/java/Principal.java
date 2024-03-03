
import bbdd.Equipo;
import bbdd.EquipoDAO;
import bbdd.Jugador;
import bbdd.JugadorDAO;
import bbdd.Jugadores;
import bbdd.Partido;
import bbdd.PartidoDAO;
import bbdd.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
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
        /**
         * ******************CREAMOS LA CONEXIÓN**************************
         */
        String url = "jdbc:postgresql://localhost:5433/futbol";
        String user = "postgres";
        String pass = "alba1234";
        try ( Connection conexion = DriverManager.getConnection(url, user, pass);) {

            /**
             * ****************************PROBANDO********************************
             */
//            System.out.println("Conexión OK");
//            Equipo prueba = new Equipo("Probandyo equipo", "Poiyo", new Persona("Juyan", 34));
//            EquipoDAO.insertarEquipo(prueba, conexion);
//            Equipo mod = new Equipo("mmm equipo", "mm", new Persona("mm", 34));
//            mod.setEquipo_id(3);
//            EquipoDAO.modificarEquipo(mod, conexion);
//            EquipoDAO.eliminarEquipo(4, conexion); //si no existe devuelve 0
//            Jugadores j1=new Jugadores(new Persona("pep",15), new Jugador(22, "Centro", 1.80f));
//            JugadorDAO.insertarJugador(j1, conexion);
//            j1.setJugador_id(1);
//            prueba.setEquipo_id(3);
//            j1.setEquipo(prueba);
//            JugadorDAO.modificarJugador(j1, conexion);
//            JugadorDAO.eliminarJugador(1, conexion);
//            Equipo local = new Equipo("Local local", "Pontevedra", new Persona("Alba", 46));
//            local.setEquipo_id(1);
//            Equipo visitante = new Equipo("Visitante", "Poio", new Persona("Juan", 34));
//            visitante.setEquipo_id(2);
//            Partido partido = new Partido(LocalDate.now(), local, visitante);
//            PartidoDAO.insertarPartido(partido, conexion);
//            JugadorDAO.inscribirJugador(2, 3, conexion);
//            JugadorDAO.desinscribirJugador(2, conexion);
//            EquipoDAO.listarEquipo(3, conexion);
//            EquipoDAO.listarEquipos(conexion);
//            JugadorDAO.listarJugador(2, conexion);
//            JugadorDAO.listarJugador("Andres Iniesta", conexion);
//            PartidoDAO.listarPartidosLocales(1, conexion);
//            PartidoDAO.listarPartidosVisitantes(2, conexion);
//            JugadorDAO.listarJugadoresPosicion("Centro", conexion);
//            JugadorDAO.listarJugadoresDorsal(8, conexion);
//            PartidoDAO.listarPartidosFecha(LocalDate.parse("2024-03-01"), conexion);
            int opcion;

            do {
                System.out.println("           MENÚ          ");
                System.out.println("*************************");
                System.out.println("1. Insertar un equipo.");
                System.out.println("2. Modificar un equipo.");
                System.out.println("3. Eliminar un equipo.");
                System.out.println("4. Insertar un jugador.");
                System.out.println("5. Modificar un jugador.");
                System.out.println("6. Eliminar un jugador.");
                System.out.println("7. Insertar un partido.");
                System.out.println("8. Modificar un partido.");
                System.out.println("9. Eliminar un partido.");
                System.out.println("10. Inscribir un jugador en un equipo.");
                System.out.println("11. Desinscribir un jugador en un equipo.");
                System.out.println("12. Listar toda la información de un Equipo buscándolo por id.");
                System.out.println("13. Listar toda la información de todos los Equipos.");
                System.out.println("14. Listar la información de un Jugador buscándolo por id.");
                System.out.println("15. Listar la información de un Jugador buscándolo por nombre.");
                System.out.println("16. Buscar partidos en los que un determinado equipo jugara como local.");
                System.out.println("17. Buscar partidos en los que un determinado equipo jugara como visitante.");
                System.out.println("18. Obtener toda la información de los jugadores que jueguen en una determinada posición.");
                System.out.println("19. Obtener toda la información de los jugadores según su dorsal.");
                System.out.println("20. Obtener todos los partidos según la fecha.");
                System.out.println("Pulsa 0 para Salir");
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 0:
                        System.out.println("Salir");
                        break;
                    case 1:
                        insertarEquipo();
                        break;
                    case 2:
                        modificarEquipo();
                        break;
                    case 3:
                        eliminarEquipo();
                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                    case 9:

                        break;
                    case 10:

                        break;
                    case 11:

                        break;
                    case 12:

                        break;
                    case 13:

                        break;
                    case 14:

                        break;
                    case 15:

                        break;
                    case 16:

                        break;
                    case 17:

                        break;
                    case 18:

                        break;
                    case 19:

                        break;
                    case 20:

                        break;

                    default:
                        System.out.println("Opción inválida");
                        break;
                }
            } while (opcion != 0);

        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        /**
         * ****************************************************************
         */
    }

}
