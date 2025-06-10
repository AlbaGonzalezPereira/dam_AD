
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
                        insertarEquipo(conexion);
                        break;
                    case 2:
                        modificarEquipo(conexion);
                        break;
                    case 3:
                        eliminarEquipo(conexion);
                        break;
                    case 4:
                        insertarJugador(conexion);
                        break;
                    case 5:
                        modificarJugador(conexion);
                        break;
                    case 6:
                        eliminarJugador(conexion);
                        break;
                    case 7:
                        insertarPartido(conexion);
                        break;
                    case 8:
                        modificarPartido(conexion);
                        break;
                    case 9:
                        eliminarPartido(conexion);
                        break;
                    case 10:
                        inscribirJugador(conexion);
                        break;
                    case 11:
                        desinscribirJugador(conexion);
                        break;
                    case 12:
                        listarEquipoId(conexion);
                        break;
                    case 13:
                        listarEquipos(conexion);
                        break;
                    case 14:
                        listarJugadorId(conexion);
                        break;
                    case 15:
                        listarJugadorNombre(conexion);
                        break;
                    case 16:
                        listarPartidosLocal(conexion);
                        break;
                    case 17:
                        listarPartidosVisitante(conexion);
                        break;
                    case 18:
                        listarJugadoresPosicion(conexion);
                        break;
                    case 19:
                        listarJugadoresDorsal(conexion);
                        break;
                    case 20:
                        listarPartidosFecha(conexion);
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

    private static void insertarEquipo(Connection conexion) {
        System.out.println("Introduce el nombre del equipo: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce la localidad del equipo: ");
        String localidad = sc.nextLine();
        System.out.println("Introduce el nombre del entrenador: ");
        String nombre_entrenador = sc.nextLine();
        System.out.println("Introduce la edad del entrenador: ");
        int edad_entrenador = Integer.parseInt(sc.nextLine());
        Equipo equipo = new Equipo(nombre, localidad, new Persona(nombre_entrenador, edad_entrenador));
        EquipoDAO.insertarEquipo(equipo, conexion);
    }

    private static void modificarEquipo(Connection conexion) {
        System.out.println("Introduce el id del equipo a modificar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el nuevo nombre del equipo: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce la nueva localidad del equipo: ");
        String localidad = sc.nextLine();
        System.out.println("Introduce el nuevo nombre del entrenador: ");
        String nombre_entrenador = sc.nextLine();
        System.out.println("Introduce la nueva edad del entrenador: ");
        int edad_entrenador = Integer.parseInt(sc.nextLine());
        Equipo equipo = new Equipo(nombre, localidad, new Persona(nombre_entrenador, edad_entrenador));
        equipo.setEquipo_id(id);
        EquipoDAO.modificarEquipo(equipo, conexion);
    }

    private static void eliminarEquipo(Connection conexion) {
        System.out.println("Introduce el id del equipo a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        EquipoDAO.eliminarEquipo(id, conexion);
    }

    private static void insertarJugador(Connection conexion) {
        System.out.println("Introduce el nombre del jugador: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce la edad del jugador: ");
        int edad = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce la posición del jugador: ");
        String posicion = sc.nextLine();
        System.out.println("Introduce la altura del jugador: ");
        float altura = Float.parseFloat(sc.nextLine());
        Jugador jugador = new Jugador(edad, posicion, altura);
        Jugadores j = new Jugadores(new Persona(nombre, edad), jugador);
        JugadorDAO.insertarJugador(j, conexion);
    }

    private static void modificarJugador(Connection conexion) {
        System.out.println("Introduce el id del jugador a modificar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el nuevo nombre del jugador: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce la nueva edad del jugador: ");
        int edad = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce la nueva posición del jugador: ");
        String posicion = sc.nextLine();
        System.out.println("Introduce la nueva altura del jugador: ");
        float altura = Float.parseFloat(sc.nextLine());
        Jugador jugador = new Jugador(edad, posicion, altura);
        Jugadores j = new Jugadores(new Persona(nombre, edad), jugador);
        j.setJugador_id(id);
        JugadorDAO.modificarJugador(j, conexion);
    }

    private static void eliminarJugador(Connection conexion) {
        System.out.println("Introduce el id del jugador a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        JugadorDAO.eliminarJugador(id, conexion);
    }

    private static void insertarPartido(Connection conexion) {
        System.out.println("Introduce la fecha del partido (yyyy-mm-dd): ");
        String fecha = sc.nextLine();
        LocalDate fecha_partido = LocalDate.parse(fecha);
        System.out.println("Introduce el id del equipo local: ");
        int id_local = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el id del equipo visitante: ");
        int id_visitante = Integer.parseInt(sc.nextLine());
        Equipo local = new Equipo();
        local.setEquipo_id(id_local);
        Equipo visitante = new Equipo();
        visitante.setEquipo_id(id_visitante);
        Partido partido = new Partido(fecha_partido, local, visitante);
        PartidoDAO.insertarPartido(partido, conexion);
    }

    private static void modificarPartido(Connection conexion) {
        System.out.println("Introduce el id del partido a modificar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce la nueva fecha del partido (yyyy-mm-dd): ");
        String fecha = sc.nextLine();
        LocalDate fecha_partido = LocalDate.parse(fecha);
        System.out.println("Introduce el nuevo id del equipo local: ");
        int id_local = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el nuevo id del equipo visitante: ");
        int id_visitante = Integer.parseInt(sc.nextLine());
        Equipo local = new Equipo();
        local.setEquipo_id(id_local);
        Equipo visitante = new Equipo();
        visitante.setEquipo_id(id_visitante);
        Partido partido = new Partido(fecha_partido, local, visitante);
        partido.setPartido_id(id);
        PartidoDAO.modificarPartido(partido, conexion);
    }

    private static void eliminarPartido(Connection conexion) {
        System.out.println("Introduce el id del partido a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        PartidoDAO.eliminarPartido(id, conexion);
    }

    private static void inscribirJugador(Connection conexion) {
        System.out.println("Introduce el id del jugador a inscribir: ");
        int id_jugador = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el id del equipo en el que inscribir al jugador: ");
        int id_equipo = Integer.parseInt(sc.nextLine());
        JugadorDAO.inscribirJugador(id_jugador, id_equipo, conexion);
    }

    private static void desinscribirJugador(Connection conexion) {
        System.out.println("Introduce el id del jugador a desinscribir: ");
        int id_jugador = Integer.parseInt(sc.nextLine());
        JugadorDAO.desinscribirJugador(id_jugador, conexion);
    }

    private static void listarEquipoId(Connection conexion) {
        System.out.println("Introduce el id del equipo a buscar: ");
        int id = Integer.parseInt(sc.nextLine());
        EquipoDAO.listarEquipo(id, conexion);
    }

    private static void listarEquipos(Connection conexion) {
        EquipoDAO.listarEquipos(conexion);
    }

    private static void listarJugadorId(Connection conexion) {
        System.out.println("Introduce el id del jugador a buscar: ");
        int id = Integer.parseInt(sc.nextLine());
        JugadorDAO.listarJugador(id, conexion);
    }

    private static void listarJugadorNombre(Connection conexion) {
        System.out.println("Introduce el nombre del jugador a buscar: ");
        String nombre = sc.nextLine();
        JugadorDAO.listarJugador(nombre, conexion);
    }

    private static void listarPartidosLocal(Connection conexion) {
        System.out.println("Introduce el id del equipo local: ");
        int id_local = Integer.parseInt(sc.nextLine());
        PartidoDAO.listarPartidosLocales(id_local, conexion);
    }

    private static void listarPartidosVisitante(Connection conexion) {
        System.out.println("Introduce el id del equipo visitante: ");
        int id_visitante = Integer.parseInt(sc.nextLine());
        PartidoDAO.listarPartidosVisitantes(id_visitante, conexion);
    }

    private static void listarJugadoresPosicion(Connection conexion) {
        System.out.println("Introduce la posición de los jugadores a buscar: ");
        String posicion = sc.nextLine();
        JugadorDAO.listarJugadoresPosicion(posicion, conexion);
    }

    private static void listarJugadoresDorsal(Connection conexion) {
        System.out.println("Introduce el dorsal de los jugadores a buscar: ");
        int dorsal = Integer.parseInt(sc.nextLine());
        JugadorDAO.listarJugadoresDorsal(dorsal, conexion);
    }

    private static void listarPartidosFecha(Connection conexion) {
        System.out.println("Introduce la fecha de los partidos a buscar (yyyy-mm-dd): ");
        String fecha = sc.nextLine();
        LocalDate fecha_partido = LocalDate.parse(fecha);
        PartidoDAO.listarPartidosFecha(fecha_partido, conexion);
    }
}
