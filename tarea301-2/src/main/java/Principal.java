
import entidades.Cita;
import entidades.Doctor;
import entidades.Hospital;
import entidades.Paciente;
import java.time.LocalDate;
import java.util.Scanner;
import org.hibernate.Session;
import repositorios.*;

/**
 *
 * @author alba_
 */
public class Principal {

    static public Scanner sc = new Scanner(System.in);
    static RepositorioDoctor repoDoc;
    static RepositorioPaciente repoPac;
    static RepositorioCita repoCit;
    static RepositorioRecibe repoRec;
    static RepositorioTratamiento repoTrat;
    static RepositorioHospital repoHosp;

    public static void main(String[] args) {

        Session sesion = HibernateUtil.get().openSession();
        repoDoc = new RepositorioDoctor(sesion);
        repoPac = new RepositorioPaciente(sesion);
        repoCit = new RepositorioCita(sesion);
        repoRec = new RepositorioRecibe(sesion);
        repoTrat = new RepositorioTratamiento(sesion);
        repoHosp = new RepositorioHospital(sesion);
        int opcion;
        do {

            System.out.println("------------------MENÚ------------------");
            System.out.println("1. Crear un nuevo doctor");
            System.out.println("2. Borrar por id un doctor");
            System.out.println("3. Modificar los datos de un doctor");
            System.out.println("4. Crear un nuevo paciente");
            System.out.println("5. Borrar por nombre un paciente");
            System.out.println("6. Modificar los datos de un paciente");
            System.out.println("7. Asignar un doctor a un paciente");
            System.out.println("8. Indicar la fecha de fin del tratamiento de un paciente");
            System.out.println("9. Cambiar el hospital de un tratamiento");
            System.out.println("10. Mostrar los datos de un paciente");
            System.out.println("11. Mostrar los datos de los tratamientos y el hospital en el que se realiza");
            System.out.println("12. Mostrar el número total de tratamientos que tiene cada hospital");
            System.out.println("0. Salir");
            System.out.println("Escoge una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 0:
                    break;
                case 1:
                    crearDoctor();
                    break;
                case 2:
                    borrarDoctor();
                    break;
                case 3:
                    modificarDoctor();
                    break;
                case 4:
                    crearPaciente();
                    break;
                case 5:
                    borrarPacienteByName();
                    break;
                case 6:
                    modificarPaciente();
                    break;
                case 7:
                    asignarDoctor();
                    break;
                case 8:
                    asignarFinTratamiento();
                    break;
                case 9:
                    cambiarHospital();
                    break;
                case 10:
                    mostrarDatosPaciente();
                    break;
                case 11:
                    mostrarDatosTratamientosByHospital();
                    break;
                case 12:
                    mostrarTotalTratamientosByHospital();
                    break;
                default:
                    System.out.println("Escoge una opción correcta");
            }
            
        } while (opcion != 0);
        
        sesion.close();
    }

    /**
     * Crear los datos de un doctor.
     */
    private static void crearDoctor() {
        System.out.println("Ingrese el nombre del doctor: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese la especialidad: ");
        String especialidad = sc.nextLine();
        System.out.println("Ingrese el teléfono del doctor: ");
        String tlf = sc.nextLine();

        int idDoctor = repoDoc.devolverUltimoId() + 1;

        Doctor doctor = new Doctor(idDoctor, nombre, especialidad, tlf, null);
        int creado = repoDoc.crear(doctor);
        if (creado > 0) {
            System.out.println("Se ha creado el doctor correctamente");
        }
    }

    /**
     * borrar (por id) los datos de un doctor.
     */
    private static void borrarDoctor() {
        System.out.println("Ingrese el id del doctor a borrar: ");
        int id = Integer.parseInt(sc.nextLine());
        int idBorrado = repoDoc.borrar(id);
        if (idBorrado > 0) {
            System.out.println("Se ha borrado el doctor correctamente");
        } else {
            System.out.println("No se ha borrado el doctor");
        }
    }

    /**
     * modificar por id los datos de un doctor.
     */
    private static void modificarDoctor() {
        System.out.println("Ingrese el id del doctor a modificar: ");
        int id = Integer.parseInt(sc.nextLine());
        Doctor doctor = repoDoc.buscarById(id);
        if (doctor == null) {
            System.out.println("Ingrese un id correcto");
        } else {
            System.out.println("Ingrese el nuevo nombre del doctor: ");
            String nombre = sc.nextLine();
            System.out.println("Ingrese la nueva especialidad: ");
            String especialidad = sc.nextLine();
            System.out.println("Ingrese el nuevo teléfono del doctor: ");
            String tlf = sc.nextLine();
            doctor.setNombre(nombre);
            doctor.setEspecialidad(especialidad);
            doctor.setTelefono(tlf);
            int idModificado = repoDoc.modificar(doctor);
            if (idModificado > 0) {
                System.out.println("Se han modificado los datos del doctor con id " + idModificado + " correctamente");
            } else {
                System.out.println("No se han podido modificar los datos del doctor");
            }
        }
    }

    /**
     * Crear los datos de un paciente.
     */
    private static void crearPaciente() {
        System.out.println("Ingrese el nombre del paciente: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese la fecha de nacimiento (dd-mm-aaaa): ");
        String fecha = sc.nextLine();
        System.out.println("Ingrese la dirección del paciente: ");
        String direccion = sc.nextLine();

        int idPaciente = repoPac.devolverUltimoId() + 1; //creamos el id del paciente
        // creamos la fecha a partir del string:
        int dia = Integer.parseInt(fecha.split("-")[0]);
        int mes = Integer.parseInt(fecha.split("-")[1]);
        int anho = Integer.parseInt(fecha.split("-")[2]);
        LocalDate fechaNac = LocalDate.of(anho, mes, dia);

        // creamos el paciente con los datos datos:
        Paciente paciente = new Paciente(idPaciente, nombre, fechaNac, direccion, null, null);
        int creado = repoPac.crear(paciente);
        if (creado > 0) {
            System.out.println("Se ha creado el paciente correctamente");
        }

    }

    /**
     * modificar (por nombre) los datos de un paciente.
     */
    private static void modificarPaciente() {
        System.out.println("Ingrese el nombre del paciente a modificar: ");
        String nombre = sc.nextLine();
        Paciente paciente = repoPac.buscarByName(nombre);
        if (paciente == null) {
            System.out.println("Ingrese un nombre de paciente correcto");
        } else {
            System.out.println("Ingrese el nuevo nombre del paciente: ");
            String nombrePac = sc.nextLine();
            System.out.println("Ingrese la nueva fecha de nacimiento (dd-mm-aaaa): ");
            String fecha = sc.nextLine();
            System.out.println("Ingrese la nueva dirección del paciente: ");
            String direccion = sc.nextLine();

            int dia = Integer.parseInt(fecha.split("-")[0]);
            int mes = Integer.parseInt(fecha.split("-")[1]);
            int anho = Integer.parseInt(fecha.split("-")[2]);
            LocalDate fechaNac = LocalDate.of(anho, mes, dia);

            paciente.setNombre(nombrePac);
            paciente.setFecha_nacimiento(fechaNac);
            paciente.setDireccion(direccion);

            int idModificado = repoPac.modificar(paciente);
            if (idModificado > 0) {
                System.out.println("Se han modificado los datos del paciente con nombre " + nombrePac + " correctamente");
            } else {
                System.out.println("No se han podido modificar los datos del paciente");
            }
        }
    }

    /**
     * borrar los datos de un paciente.
     */
    private static void borrarPacienteByName() {
        System.out.println("Ingrese el nombre del paciente a borrar: ");
        String nombre = sc.nextLine();
        Paciente paciente = repoPac.buscarByName(nombre);
        int idBorrado = repoPac.borrar(paciente);
        if (idBorrado > 0) {
            System.out.println("Se ha borrado el paciente con nombre " + nombre + " correctamente");
        } else {
            System.out.println("No se ha borrado el paciente");
        }

    }

    /**
     * Asignar un doctor a un paciente.
     * La asignación se hará a partir del nombre del doctor y del paciente.
     * Se pedirá por teclado introducir el nombre del doctor y del paciente.
     */
    private static void asignarDoctor() {
        System.out.println("Ingrese la fecha de la cita(dd-mm-aaaa): ");
        String fecha = sc.nextLine();
        System.out.println("Ingrese el nombre del paciente: ");
        String nombrePac = sc.nextLine();
        System.out.println("Ingrese el nombre del doctor: ");
        String nombreDoc = sc.nextLine();

        int dia = Integer.parseInt(fecha.split("-")[0]);
        int mes = Integer.parseInt(fecha.split("-")[1]);
        int anho = Integer.parseInt(fecha.split("-")[2]);
        LocalDate fechaCita = LocalDate.of(anho, mes, dia);

        Doctor doctor = repoDoc.buscarByName(nombreDoc);
        Paciente paciente = repoPac.buscarByName(nombrePac);

        //creamos una cita y modificamos datos:
        Cita cita = new Cita();
        cita.setDoctor(doctor);
        cita.setFecha(fechaCita);
        cita.setPaciente(paciente);

        int citaCreada = repoCit.crear(cita);
        if (citaCreada > 0) {
            System.out.println("Se ha asignado la cita con el doctor correctamente");
        }
    }

    /**
     * Indicar la fecha de fin del tratamiento de un paciente.
     * El método recibirá el nombre del paciente, la fecha de inicio, el tipo y la fecha de fin del tratamiento.
     */
    private static void asignarFinTratamiento() {
        System.out.println("Ingrese el nombre del paciente:");
        String nombre = sc.nextLine();
        System.out.println("Ingrese la fecha de inicio de tratamiento (dd-mm-aaaa): ");
        String fechaIni = sc.nextLine();
        System.out.println("Ingrese el tipo del tratamiento: ");
        String tipo = sc.nextLine();
        System.out.println("Ingrese la fecha de fin de tratamiento (dd-mm-aaaa): ");
        String fechaFin = sc.nextLine();

        //creamos fecha inicio y fin:
        int dia = Integer.parseInt(fechaIni.split("-")[0]);
        int mes = Integer.parseInt(fechaIni.split("-")[1]);
        int anho = Integer.parseInt(fechaIni.split("-")[2]);
        LocalDate fechaInicio = LocalDate.of(anho, mes, dia);

        dia = Integer.parseInt(fechaFin.split("-")[0]);
        mes = Integer.parseInt(fechaFin.split("-")[1]);
        anho = Integer.parseInt(fechaFin.split("-")[2]);
        LocalDate fechaFinal = LocalDate.of(anho, mes, dia);

        repoRec.indicarFinTratamiento(nombre, tipo, fechaInicio, fechaFinal);

    }

    /**
     * Cambiar el hospital de un tratamiento.
     * El método recibirá el id del tratamiento, el nombre del hospital en donde está ahora el tratamiento y 
     * el nombre del hospital en dónde se va a realizar el tratamiento a partir de ahora.
     */
    private static void cambiarHospital() {
        System.out.println("Ingrese el id del tratamiento:");
        int idTrat = Integer.parseInt(sc.nextLine());
        System.out.println("Ingrese el nombre del hospital actual que realiza el tratamiento: ");
        String nombreActual = sc.nextLine();
        System.out.println("Ingrese el nombre del hospital nuevo que realizará el tratamiento: ");
        String nombreNuevo = sc.nextLine();

        int cambiado = repoTrat.cambiarHospital(idTrat, nombreActual, nombreNuevo);

        if (cambiado > 0) {
            System.out.println("Se ha modificado el hospital " + nombreActual + " por el hospital " + nombreNuevo);
        }
    }

    /**
     * Mostrar los datos de un Paciente (id, nombre, fecha_nacimiento,
     * direccion, tratamientos que recibe y citas que tiene). La consulta se
     * hará a partir del nombre del Paciente que introduzca el usuario.
     */
    private static void mostrarDatosPaciente() {
        System.out.println("Introduza el nombre del paciente que quiere visualizar: ");
        String nombre = sc.nextLine();
        Paciente paciente = repoPac.buscarByName(nombre);
        if (paciente == null) {
            //System.out.println("El paciente introducido no existe");
            return;
        }
        System.out.println("id: " + paciente.getId());
        System.out.println("nombre: " + paciente.getNombre());
        System.out.println("fecha nacimiento: " + paciente.getFecha_nacimiento());
        System.out.println("dirección: " + paciente.getDireccion());
        System.out.println("tratamientos que recibe: " + paciente.getRecibes());
        System.out.println("citas: " + paciente.getCitas());
    }

    /**
     * Mostrar los datos de los tratamientos y el hospital en el que se realiza.
     * La consulta se hará a partir del nombre del hospital que introduzca el
     * usuario.
     */
    private static void mostrarDatosTratamientosByHospital() {
        System.out.println("Introduce el nombre del hospital en el que quieres ver sus tratamientos: ");
        String nombre = sc.nextLine();

        Hospital hospital = repoHosp.buscarByName(nombre);
        if (hospital != null) {
            System.out.println("Los tratamientos del hospital " + hospital.getNombre() + "ubicado en " + hospital.getUbicacion() + " son:\n" + hospital.getTratamientos());
        }
    }

    /**
     * Mostrar el número total de tratamientos que tiene cada hospital. La
     * consulta se hará a partir del nombre del hospital que introduzca el
     * usuario.
     */
    private static void mostrarTotalTratamientosByHospital() {
        System.out.println("Introduce el nombre del hospital en el que quieres ver el total de sus tratamientos: ");
        String nombre = sc.nextLine();
        Hospital hospitalEncontrado = repoHosp.buscarByName(nombre);
        if (hospitalEncontrado == null) {
            //System.out.println("No se ha encontrado el hospital");
            return;
        }

        int totalTrat = repoTrat.totalTratamientosByHospital(nombre);

        if (totalTrat > 0) {
            System.out.println("El hospital " + nombre + " tiene " + totalTrat + " tratamientos");
        } else {
            System.out.println("No se han encontrado tratamientos en el hospital " + nombre);
        }
    }

}
