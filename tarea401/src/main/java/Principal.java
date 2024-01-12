
import entidades.AsignarProyecto;
import entidades.DatosProfesionales;
import entidades.Empleado;
import entidades.Proyecto;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import repositorio.AsignarProyectoRepositorio;
import repositorio.DatosProfesionalesRepositorio;
import repositorio.EmpleadoRepositorio;
import repositorio.ProyectoRepositorio;

/**
 *
 * @author alba_
 */
public class Principal {

    static Scanner sc = new Scanner(System.in);
    static EmpleadoRepositorio empRepo;
    static ProyectoRepositorio proRepo;
    static DatosProfesionalesRepositorio datProRepo;
    static AsignarProyectoRepositorio asigProRepo;
    static int opcion;

    public static void main(String[] args) {
        Session sesion = HibernateUtil.get().openSession();
        empRepo = new EmpleadoRepositorio(sesion);
        proRepo = new ProyectoRepositorio(sesion);
        datProRepo = new DatosProfesionalesRepositorio(sesion);
        asigProRepo = new AsignarProyectoRepositorio(sesion);

        System.out.println("***************MENÚ***************");

        do {
            sesion.clear();//limpiamos la sesión
            //Crear, borrar y modificar los datos de un empleado.
            System.out.println("1. Crear Empleado");
            System.out.println("2. Modificar Empleado");
            System.out.println("3. Borrar Empleado");
            //Crear, borrar y modificar los datos de un proyecto.
            System.out.println("4. Crear Proyecto");
            System.out.println("5. Modificar Proyecto");
            System.out.println("6. Borrar Proyecto");
            System.out.println("7. Asignar un empleado a un proyecto");
            System.out.println("8. Indicar el fin de la participación de un empleado en un proyecto");
            System.out.println("9. Cambiar el jefe de un proyecto");
            System.out.println("10. Mostrar los datos de un proyecto");
            System.out.println("11. Mostrar los datos de los empleados que están en plantilla");
            System.out.println("12. Mostrar los empleados que son jefes de proyecto");
            System.out.println("13. Salir");
            System.out.println("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    crearEmpleado();
                    break;
                case 2:
                    modificarEmpleado();
                    break;
                case 3:
                    borrarEmpleado();
                    break;
                case 4:
                    crearProyecto();
                    break;
                case 5:
                    modificarProyecto();
                    break;
                case 6:
                    borrarProyecto();
                    break;
                case 7:
                    asignarEmpleadoProyecto();
                    break;
                case 8:
                    indicarFin();
                    break;
                case 9:
                    cambiarJefeProyecto();
                    break;
                case 10:
                    mostrarDatosProyecto();
                    break;
                case 11:
                    mostrarPlantilla();
                    break;
                case 12:
                    mostrarEmpleadosJefes();
                    break;
                case 13:
                    break;
            }
        } while (opcion != 13);

        sesion.close();//cerramos session
    }

    private static void crearEmpleado() {
        System.out.println("Introduzca el dni");
        String dni = sc.nextLine();
        System.out.println("Introduzca el nombre: ");
        String nombre = sc.nextLine();
        Empleado emp = new Empleado(dni, nombre.toUpperCase());
        empRepo.insertar(emp);
    }

    private static void modificarEmpleado() {
        System.out.println("Introduzca el dni del empleado");
        String dni = sc.nextLine();
        try {
            Empleado empBuscar = empRepo.buscarById(dni);
            if (empBuscar != null) {
                System.out.println("El empleado encontrado es: " + empBuscar);
                System.out.println("Introduzca el nuevo nombre: ");
                String nombre = sc.nextLine();
                empBuscar.setNombre(nombre.toUpperCase());
                empRepo.actualizar(empBuscar);
            }

        } catch (NoResultException e) {
            System.out.println("El dni introducido no corresponde con ningún empleado");
        }

    }

    private static void borrarEmpleado() {
        System.out.println("Introduce el dni del empleado a eliminar: ");
        String dni = sc.nextLine();

        try {
            Empleado empEliminar = empRepo.buscarById(dni);
            if (empEliminar != null) {
                System.out.println("El empleado encontrado es: " + empEliminar);
                empRepo.borrar(empEliminar);
                System.out.println("Se he eliminado el empleado con dni " + dni + " correctamente");
            }

        } catch (NoResultException e) {
            System.out.println("El dni introducido no corresponde con ningún empleado");
        }
    }

    private static void crearProyecto() {
        try {
            System.out.println("Introduzca el nombre del proyecto");
            String nombre = sc.nextLine();
            System.out.println("Introduzca la fecha de inicio (aaaa-mm-dd): ");
            String fechaInicio = sc.nextLine();
            LocalDate fecha = LocalDate.parse(fechaInicio);
            System.out.println("Introduzca el dni del jefe del proyecto: ");
            String dniJefe = sc.nextLine();
            Empleado emp = empRepo.buscarById(dniJefe);
            Proyecto proyecto = new Proyecto(nombre.toUpperCase(), fecha, emp);
            proRepo.insertar(proyecto);
        } catch (NoResultException e) {
            System.out.println("El dni introducido no corresponde con ningún empleado.");
        } catch (DateTimeParseException e) {
            System.out.println("La fecha no tiene un formato adecuado.");
        }
    }

    private static void modificarProyecto() {
        System.out.println("Introduzca el id del proyecto");
        int id = Integer.parseInt(sc.nextLine());
        try {
            Proyecto proBuscar = proRepo.buscarById(id);
            if (proBuscar != null) {
                System.out.println("El proyecto encontrado es: " + proBuscar);
                System.out.println("Introduzca el nuevo nombre: ");
                String nombre = sc.nextLine();
                System.out.println("Introduzca la nueva fecha de inicio (aaaa-mm-dd): ");
                String fechaInicio = sc.nextLine();
                LocalDate fechaIni = LocalDate.parse(fechaInicio);
                System.out.println("Introduzca la nueva fecha de fin (aaaa-mm-dd): ");
                String fechaFin = sc.nextLine();
                LocalDate fechaFinal;
                if (fechaFin.equals("")) {
                    fechaFinal = null;
                } else {
                    fechaFinal = LocalDate.parse(fechaFin);
                }
                System.out.println("Introduzca el dni del jefe del proyecto: ");
                String dniJefe = sc.nextLine();
                Empleado emp = empRepo.buscarById(dniJefe);
                proBuscar.setJefeProyecto(emp);
                proBuscar.setFechaFin(fechaFinal);
                System.out.println(fechaIni);
                proBuscar.setFechaInicio(fechaIni);
                proBuscar.setNombreProyecto(nombre);
                proRepo.actualizar(proBuscar);
            }
        } catch (NoResultException e) {
            System.out.println("El id introducido no corresponde con ningún proyecto");
        } catch (DateTimeParseException e) {
            System.out.println("La fecha no tiene un formato adecuado.");
        }
    }

    private static void borrarProyecto() {
        System.out.println("Introduce el id del proyecto a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());

        try {
            Proyecto proEliminar = proRepo.buscarById(id);
            if (proEliminar != null) {
                System.out.println("El proyecto encontrado es: " + proEliminar);
                proRepo.borrar(proEliminar);
                System.out.println("Se he eliminado el proyecto con id " + id + " correctamente");
            }

        } catch (NoResultException e) {
            System.out.println("El id introducido no corresponde con ningún proyecto");
        }

    }

    private static void asignarEmpleadoProyecto() {
        try {
            System.out.println("Introduzca el id del proyecto");
            int id = Integer.parseInt(sc.nextLine());
            Proyecto p = proRepo.buscarById(id);

            System.out.println("Introduzca el dni del empleado: ");
            String dni = sc.nextLine();
            Empleado e = empRepo.buscarById(dni);

            AsignarProyecto asig = new AsignarProyecto(e, p, LocalDate.now(), null);
            asigProRepo.insertar(asig);

        } catch (NoResultException e) {
            System.out.println("El proyecto o el empleado introducido no existe.");
        }catch(ConstraintViolationException e){
            System.out.println("El proyecto ya tiene asignado ese empleado.");
        }catch(PersistenceException e){
            System.out.println("El proyecto ya tiene asignado ese empleado.");
        }

    }

    private static void mostrarDatosProyecto() {
        System.out.println("Introduzca el id del proyecto");
        int id = Integer.parseInt(sc.nextLine());
        try {
            Proyecto proBuscar = proRepo.buscarById(id);
            if (proBuscar != null) {
                System.out.println("El proyecto encontrado es: " + proBuscar);
            }
        } catch (NoResultException e) {
            System.out.println("El id introducido no corresponde con ningún proyecto");
        }

    }

    private static void mostrarPlantilla() {
        List<DatosProfesionales> datosPlantilla = datProRepo.buscarAll();
        System.out.println("*****Lita de empleados******");
        int contador = 1;
        for (DatosProfesionales datosProfesionales : datosPlantilla) {
            System.out.println("Empleado " + contador + " = " + datosProfesionales);
            contador++;
        }

    }

    private static void mostrarEmpleadosJefes() {
        List<Proyecto> proyectos = proRepo.buscarAll();
        Set<Empleado> jefes = new HashSet<>(); //para que no salga duplicados los jefes de proyecto
        for (Proyecto proyecto : proyectos) {
            jefes.add(proyecto.getJefeProyecto());
        }

        for (Empleado jefe : jefes) {
            System.out.println("jefe = " + jefe);
        }
    }

    private static void cambiarJefeProyecto() {
        System.out.println("Introduzca el id del proyecto");
        int id = Integer.parseInt(sc.nextLine());
        try {
            Proyecto proBuscar = proRepo.buscarById(id);
            if (proBuscar != null) {
                System.out.println("El proyecto encontrado es: " + proBuscar);
                System.out.println("Introduzca el dni del jefe del proyecto: ");
                String dniJefe = sc.nextLine();
                try {
                    Empleado emp = empRepo.buscarById(dniJefe);
                    proBuscar.setJefeProyecto(emp);
                } catch (NoResultException e) {
                    System.out.println("El dni introducido no corresponde con ningún empleado.");
                }

                proRepo.actualizar(proBuscar);

            }
        } catch (NoResultException e) {
            System.out.println("El id introducido no corresponde con ningún proyecto.");
        }
    }

    private static void indicarFin() {
        try {
            System.out.println("Introduzca el id del proyecto");
            int id = Integer.parseInt(sc.nextLine());
            Proyecto p = proRepo.buscarById(id);

            System.out.println("Introduzca el dni del empleado: ");
            String dni = sc.nextLine();
            Empleado e = empRepo.buscarById(dni);

            AsignarProyecto asig = asigProRepo.buscarById(e, p);
            asig.setFechaFin(LocalDate.now());
            asigProRepo.actualizar(asig);

        } catch (NoResultException e) {
            System.out.println("El empleado no está signado a ese proyecto introducido.");
        }

    }
}
