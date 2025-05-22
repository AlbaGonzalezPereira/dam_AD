
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alba gonzález
 */
public class Ejercicio2 {

    static Scanner sc = new Scanner(System.in);
    static int opcion;

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5433/academia";
        String user = "postgres";
        String pass = "alba1234";

        String menu = "1. Insertar nueva inscripción\n"
                + "2. Actualizar el email de un estudiante\n"
                + "3. Eliminar la información de un curso\n"
                + "4. Consulta 1\n"
                + "5. Consulta 2\n"
                + "6. Consulta 3\n"
                + "7. Consulta 4\n"
                + "8. Consulta 5\n"
                + "9. Salir";

        try ( Connection conexion = DriverManager.getConnection(url, user, pass);) {
            do {
                System.out.println(menu);
                System.out.println("Elige una opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        insertarInscripcion(conexion);
                        break;
                    case 2:
                        actualizarEmail(conexion);
                        break;
                    case 3:
                        eliminarInfoCurso(conexion);
                        break;
                    case 4:
                        listarEstudiantes(conexion);
                        break;
                    case 5:
                        obtenerPromedioEstudiantes(conexion);
                        break;
                    case 6:
                        obtenerDatosCurso(conexion);
                        break;
                    case 7:
                        obtenerDatosCursosMayores(conexion);
                        break;
                    case 8:
                        obtenerCantidadCursosEstudiante(conexion);
                        break;
                    case 9:
                        break;
                    default:
                        System.out.println("Elige una opción de las disponibles");

                }

            } while (opcion != 0);

        } catch (SQLException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }
        // consultas:
//        String sqlInsertar = "INSERT INTO inscripciones(curso_id, estudiante_id) VALUES(?,?);";
//        String sqlAct = "UPDATE Estudiantes SET info_estudiante.email = ? WHERE estudiante_id = ? ";
//        String sqlEliminar = "DELETE info_adicional.nivel, info_adicional.duracion_semanas, info_adicional.horario FROM cursos WHERE curso_id = ?";
//        
//        String sqlPromedio = "SELECT AVG((info_estudiante).edad) FROM estudiantes";
//        String sqlCon3 = "SELECT nombre_curso, descripcion, nombre FROM cursos INNER JOIN profesores\n"
//                + "ON cursos.profesor_id = profesores.profesor_id WHERE precio > 180 ";
//
//        try ( Connection conexion = DriverManager.getConnection(url, user, pass);  PreparedStatement stmtIns = conexion.prepareStatement(sqlInsertar);  PreparedStatement stmtAct = conexion.prepareStatement(sqlAct);  PreparedStatement stmtElim = conexion.prepareStatement(sqlEliminar);  PreparedStatement stmtConsulta = conexion.prepareStatement(sqlConsulta);  PreparedStatement stmtCon3 = conexion.prepareStatement(sqlCon3);) {
//            System.out.println("----------CONSULTAS--------------");
//            System.out.println("a. Insertar una inscripción: ");
//            System.out.println("Inscribimos en el curso con id 5 al estudiante con id 4");
//            stmtIns.setInt(1, 5);
//            stmtIns.setInt(2, 4);
//            int introducido = stmtIns.executeUpdate();
//            if (introducido > 0) {
//                System.out.println("inscripción realizada correctamente");
//            } else {
//                System.out.println("No se ha podido realizar la inscripción");
//            }
//
//            System.out.println("---------------------------------");
//            System.out.println("b. Actualizar el valor de email de un estudiante");
//            System.out.println("Actualizamos el email del estudiante con id = 2");
//            stmtAct.setString(1, "alba@gmail.com");
//            stmtAct.setInt(2, 2);
//            int actualizado = stmtAct.executeUpdate();
//            if (actualizado > 0) {
//                System.out.println("El email se ha actualizado correctamente");
//            } else {
//                System.out.println("No se ha podido actualizar el email");
//            }
//
//            System.out.println("---------------------------------");
//            System.out.println("c. Eliminar la información de un curso: ");
//            System.out.println("Eliminamos la información del curso con id = 2");
//            stmtElim.setInt(1, 2);
//            int eliminado = stmtElim.executeUpdate();
//            if (eliminado > 0) {
//                System.out.println("Se ha eliminado correctamente la información");
//            } else {
//                System.out.println("No se ha eliminado la información");
//            }
//
//            System.out.println("---------------------------------");
//            System.out.println("d. Consulta 1: ");
//            
//
//            System.out.println("---------------------------------");
//            System.out.println("e. Consulta 2: ");
//            PreparedStatement stmtProm = conexion.prepareStatement(sqlPromedio);
//            ResultSet resultado = stmtProm.executeQuery();
//            if (resultado.next()) {
//                System.out.println("El promedio de las edades de los estudiantes es: " + resultado);
//            }
//
//            System.out.println("---------------------------------");
//            System.out.println("f. Consulta 3:");
//
//            ResultSet resultadosC3 = stmtCon3.executeQuery();
//            while (resultadosC3.next()) {
//                System.out.println("Nombre curso: " + resultados.getString(1) + "Descripción: " + resultados.getString(2) + "Profesor: " + resultados.getString(3));
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
    }

    private static void listarEstudiantes(Connection con) {
        String sqlListar = "SELECT (info_estudiante).nombre, (info_estudiante).email FROM estudiantes";

        try ( PreparedStatement stmt = con.prepareStatement(sqlListar);) {
            ResultSet resultados = stmt.executeQuery();
            while (resultados.next()) {
                System.out.println("Nombre: " + resultados.getString(1) + ", Email: " + resultados.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void obtenerPromedioEstudiantes(Connection con) {
        String sqlPromedio = "SELECT AVG((info_estudiante).edad) FROM estudiantes";
        try ( PreparedStatement stmt = con.prepareStatement(sqlPromedio);) {
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                System.out.printf("El promedio de las edades de los estudiantes es:  %.2f \n", resultado.getFloat(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void obtenerDatosCurso(Connection con) {
        String sqlCurso = "SELECT nombre_curso, descripcion, nombre FROM cursos INNER JOIN profesores\n"
                + "ON cursos.profesor_id = profesores.profesor_id WHERE precio > 180 ";
        try ( PreparedStatement stmt = con.prepareStatement(sqlCurso);) {
            ResultSet resultados = stmt.executeQuery();
            while (resultados.next()) {
                System.out.println("Nombre curso: " + resultados.getString(1) + ", Descripción: " + resultados.getString(2) + ", Profesor: " + resultados.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void obtenerDatosCursosMayores(Connection con) {
        String sqlCursosMayores = "SELECT nombre_curso,descripcion FROM public.cursos\n"
                + "INNER JOIN inscripciones\n"
                + "on(inscripciones.curso_id=cursos.curso_id)\n"
                + "INNER JOIN estudiantes\n"
                + "on(estudiantes.estudiante_id=inscripciones.estudiante_id)\n"
                + "where (estudiantes.info_estudiante).edad>28";

        try ( PreparedStatement stmt = con.prepareStatement(sqlCursosMayores);) {
            ResultSet resultados = stmt.executeQuery();
            while (resultados.next()) {
                System.out.println("Nombre: " + resultados.getString(1) + ", Descripcion: " + resultados.getString(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void obtenerCantidadCursosEstudiante(Connection con) {
        String sqlCantidadCursos = "SELECT (info_estudiante).nombre,count(*) FROM public.cursos\n"
                + "INNER JOIN inscripciones\n"
                + "on(inscripciones.curso_id=cursos.curso_id)\n"
                + "INNER JOIN estudiantes\n"
                + "on(estudiantes.estudiante_id=inscripciones.estudiante_id)\n"
                + "GROUP BY estudiantes.estudiante_id";

        try ( PreparedStatement stmt = con.prepareStatement(sqlCantidadCursos);) {
            ResultSet resultados = stmt.executeQuery();
            while (resultados.next()) {
                System.out.println("Nombre: " + resultados.getString(1) + ", Cantidad total de cursos: " + resultados.getInt(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void insertarInscripcion(Connection con) {
        String sqlInsertar = "INSERT INTO inscripciones(curso_id, estudiante_id) VALUES(?,?);";
        try ( PreparedStatement stmt = con.prepareStatement(sqlInsertar);) {
            System.out.println("Inscribimos en el curso con id 5 al estudiante con id 4");
            stmt.setInt(1, 5);
            stmt.setInt(2, 4);
            int introducido = stmt.executeUpdate();
            if (introducido > 0) {
                System.out.println("inscripción realizada correctamente");
            } else {
                System.out.println("No se ha podido realizar la inscripción");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void actualizarEmail(Connection con) {
        String sqlAct = "UPDATE Estudiantes SET info_estudiante.email = ? WHERE estudiante_id = ? ";
        try ( PreparedStatement stmtAct = con.prepareStatement(sqlAct);) {
            System.out.println("Actualizamos el email del estudiante con id = 2");
            stmtAct.setString(1, "alba@gmail.com");
            stmtAct.setInt(2, 2);
            int actualizado = stmtAct.executeUpdate();
            if (actualizado > 0) {
                System.out.println("El email se ha actualizado correctamente");
            } else {
                System.out.println("No se ha podido actualizar el email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void eliminarInfoCurso(Connection con) {
        String sqlEliminar = "UPDATE cursos SET info_adicional = NULL WHERE curso_id = ?";

        try ( PreparedStatement stmtElim = con.prepareStatement(sqlEliminar);) {
            System.out.println("Eliminamos la información del curso con id = 2");
            stmtElim.setInt(1, 2);
            int eliminado = stmtElim.executeUpdate();
            if (eliminado > 0) {
                System.out.println("Se ha eliminado correctamente la información");
            } else {
                System.out.println("No se ha eliminado la información");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
