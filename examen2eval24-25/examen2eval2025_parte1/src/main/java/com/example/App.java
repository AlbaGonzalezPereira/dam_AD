package com.example;

import entity.Ataque;
import entity.Permite;
import entity.Sistema;
import entity.Vulnerabilidad;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

    static Scanner sc = new Scanner(System.in);
    static int opcion;

    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        do {
            System.out.println("**************MENÚ***************");
            System.out.println("1. Crear vulnerabilidad");
            System.out.println("2. Asignar vulnerabilidad - sistema");
            System.out.println("3. Asignar vulnerabilidad - ataque");
            System.out.println("4. Consulta 1");
            System.out.println("5. Consulta 2");
            System.out.println("6. Consulta 3");
            System.out.println("7. Consulta 4");
            System.out.println("8. Salir");

            System.out.println("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    crearVulnerabilidad(session);
                    break;

                case 2:
                    asignarVulnerabilidadSistema(session);
                    break;

                case 3:
                    asignarVunerabilidadAtaque(session);
                    break;

                case 4:
                    obtenerDatosVulnerabilidad(session);
                    break;

                case 5:
                    listarDescripcionVulnerabilidad(session);
                    break;

                case 6:
                    contarVulnerabilidadesRiesgo4(session);
                    break;

                case 7:
                    listarVulnerabilidadesAtaques(session);
                    break;

                case 8:
                    break;

                default:
                    System.out.println("¡¡Debes elegir una opción correcta!!");
            }

        } while (opcion != 8);

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

    /**
     * método que crea una vulnerabilidad
     *
     * @param session
     */
    private static void crearVulnerabilidad(Session session) {
        Transaction trt = session.beginTransaction(); //iniciamos una transación
        //pedimos los datos
        System.out.println("Introduce un nombre de una vulnerabilidad: ");
        String nombre = sc.nextLine();

        System.out.println("Introduce una descripción: ");
        String descripcion = sc.nextLine();

        System.out.println("Introduce el nivel de riego: ");
        Byte nivel = Byte.valueOf(sc.nextLine());

        Vulnerabilidad nuevaVulnera = new Vulnerabilidad(nombre, descripcion, nivel); //creamos una vulnerabilidad
        session.persist(nuevaVulnera); //guardamos
        trt.commit();
    }

    /**
     * método que devuelve una vulnerabilidad a partir de un id
     *
     * @param id
     * @param session
     * @return
     */
    private static Vulnerabilidad getVulneraById(int id, Session session) {
        Vulnerabilidad vulnera = session.createQuery("FROM Vulnerabilidad v WHERE v.id_vulnerabilidad = :idV", Vulnerabilidad.class).setParameter("idV", id).uniqueResult();
        if (vulnera == null) {
            System.out.println("id no encontrado");
        }
        return vulnera;
    }

    /**
     * método que devuelve un sistema a partir de un id
     *
     * @param id
     * @param session
     * @return
     */
    private static Sistema getSistemaById(int id, Session session) {
        Sistema sistema = session.createQuery("FROM Sistema s WHERE s.id_sistema = :idS", Sistema.class).setParameter("idS", id).uniqueResult();
        if (sistema == null) {
            System.out.println("Id no encontrado");
        }
        return sistema;
    }

    /**
     * método que asigna una vulnerabilidad a un sistema. La asignación será
     * bidireccional.
     *
     * @param session
     */
    private static void asignarVulnerabilidadSistema(Session session) {
        Transaction trt = session.beginTransaction(); //iniciamos una transación
        System.out.println("Introduce el id de la vulnerabilidad: ");
        int idVulnera = Integer.parseInt(sc.nextLine());
        Vulnerabilidad vulneraEncontrado = getVulneraById(idVulnera, session);
        System.out.println("Introduce el id del sistema: ");
        int idSistema = Integer.parseInt(sc.nextLine());
        Sistema sistemaEncontrado = getSistemaById(idSistema, session);
        if (vulneraEncontrado != null && sistemaEncontrado != null) {
            vulneraEncontrado.setSistemaBidireccional(sistemaEncontrado);
            sistemaEncontrado.setVulnerabilidadBidireccional(vulneraEncontrado);
            session.merge(vulneraEncontrado);
        }
        trt.commit();
    }

    /**
     * método que devuelve un ataque a partir de un id
     *
     * @param id
     * @param session
     * @return
     */
    private static Ataque getAtaqueById(int id, Session session) {
        Ataque ataque = session.createQuery("FROM Ataque a WHERE a.id_ataque = :idA", Ataque.class).setParameter("idA", id).uniqueResult();
        if (ataque == null) {
            System.out.println("Id no encontrado");
        }
        return ataque;
    }

    /**
     * método que asigna una vulnerabilidad a un vector de ataque para un día
     * concreto. La asignación será bidireccional. La fecha puede usarse
     * Localdate.now()
     *
     * @param session
     */
    private static void asignarVunerabilidadAtaque(Session session) {
        Transaction trt = session.beginTransaction(); //iniciamos una transación
        System.out.println("Introduce el id de la vulnerabilidad: ");
        int idVulnera = Integer.parseInt(sc.nextLine());
        Vulnerabilidad vulneraEncontrado = getVulneraById(idVulnera, session);
        System.out.println("Introduce el id del ataque: ");
        int idAtaque = Integer.parseInt(sc.nextLine());
        Ataque ataqueEncontrado = getAtaqueById(idAtaque, session);
        if (vulneraEncontrado != null && ataqueEncontrado != null) {
            System.out.println("Introduce el impacto: ");
            Byte impacto = Byte.valueOf(sc.nextLine());
            Permite nuevoPermite = new Permite(vulneraEncontrado, ataqueEncontrado, impacto, LocalDate.now());
            vulneraEncontrado.setPermiteBidireccional(nuevoPermite);
            ataqueEncontrado.setPermiteBidireccional(nuevoPermite);
            session.persist(nuevoPermite);
        }
        trt.commit();
    }

    /**
     * método que obtiene el nombre, descripción y nivel de riesgo de una
     * vulnerabilidad a partir de su id.
     *
     * @param session
     */
    private static void obtenerDatosVulnerabilidad(Session session) {
        Transaction trt = session.beginTransaction(); //iniciamos una transación
        System.out.println("Introduce el id de la vulnerabilidad: ");
        int idVulnera = Integer.parseInt(sc.nextLine());

        // Consulta que selecciona solo los campos necesarios
        Object[] resultados = session.createQuery("SELECT v.nombre, v.descripcion, v.nivel_riesgo FROM Vulnerabilidad v WHERE v.id_vulnerabilidad = :idV", Object[].class).setParameter("idV", idVulnera).uniqueResult();
        // imprimimos las tres columnas:
        System.out.println("Solución consulta 1: " + resultados[0] + ", " + resultados[1] + ", " + resultados[2]);
    }

    /**
     * método que lista la descripción de la solución de la vulnerabilidad
     * “Ransomware” (Solución: Implementar copias de …).
     *
     * @param session
     */
    private static void listarDescripcionVulnerabilidad(Session session) {
        Transaction trt = session.beginTransaction(); //iniciamos una transación

        Vulnerabilidad vulneraConsulta = session.createQuery("FROM Vulnerabilidad v WHERE v.nombre = 'Ransomware'", Vulnerabilidad.class).uniqueResult();
        System.out.println("vulneraConsulta = " + vulneraConsulta.getNombre());
        // Consulta que selecciona solo los campos necesarios
        Object[] resultado = session.createQuery("SELECT s.descripcion FROM Solucion s WHERE s.vulnerabilidad = :vulnera", Object[].class).setParameter("vulnera", vulneraConsulta).uniqueResult();
        // imprimimos el resultado:
        System.out.println("Solución consulta 2: " + resultado[0]);
    }

    /**
     * método que cuenta cuántas vulnerabilidades de nivel_riesgo 4 tiene el
     * sistema “Windows 10”
     *
     * @param session
     */
    private static void contarVulnerabilidadesRiesgo4(Session session) {
        Transaction trt = session.beginTransaction(); //iniciamos una transación

        //count devuelve un Long:
        Long totalVulnera = session.createQuery("SELECT COUNT(v) FROM Sistema s JOIN s.vulnerabilidades v WHERE s.nombre = 'Windows 10' AND v.nivel_riesgo = 4", Long.class).uniqueResult();
        System.out.println("Solución consulta 3: " + totalVulnera);
    }

    /**
     * método que lista todas las vulnerabilidades con sus ataques permitidos, pero sólo si el impacto del ataque es mayor o igual a 3
     * @param session 
     */
    private static void listarVulnerabilidadesAtaques(Session session) {
        Transaction trt = session.beginTransaction(); //iniciamos una transación
        List<Object[]> resultados = session.createQuery("SELECT v.nombre, a.nombre, p.impacto FROM Permite p JOIN p.vulnerabilidad v JOIN p.ataque a WHERE p.impacto >= 3", Object[].class).list();

        for (Object[] resultado : resultados) {
            System.out.println("Solución consulta 4: " + (String) resultado[0] + ", " + (String) resultado[1] + ", " + (Byte) resultado[2]);
        }
    }

}
