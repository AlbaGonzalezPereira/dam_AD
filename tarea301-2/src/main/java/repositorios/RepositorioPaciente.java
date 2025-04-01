package repositorios;

import entidades.Paciente;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alba_
 */
public class RepositorioPaciente implements Repositorio<Paciente> {

    private Session sesion;

    //Creamos constructor con la sesion
    public RepositorioPaciente(Session sesion) {
        this.sesion = sesion;
    }

    @Override
    public int crear(Paciente paciente) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        try {
            sesion.persist(paciente);
            transacion.commit();
            return paciente.getId(); // devuelve el id del paciente creado
        } catch (Exception ex) {
            transacion.rollback();//para que no lo guarde si hay un error
            return 0;
        }
    }

    @Override
    public int modificar(Paciente paciente) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        try {
            sesion.merge(paciente);
            transacion.commit();
            return paciente.getId(); // devuelve el id del doctor modificado
        } catch (Exception ex) {
            transacion.rollback();//para que no lo guarde si hay un error
            return 0;
        }
    }

    @Override
    public int borrar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int devolverUltimoId() {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        Integer ultimoId = (Integer) sesion.createQuery("SELECT MAX(id) FROM Paciente").uniqueResult();
        transacion.commit();
        if (ultimoId == null) {
            return 0;
        }
        return ultimoId;
    }

    @Override
    public Paciente buscarById(int idPac) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        Paciente paciente = (Paciente) sesion.createQuery("FROM Paciente WHERE id = :idPac ").setParameter("idPac", idPac).uniqueResult();
        transacion.commit();
        if (paciente == null) {
            System.out.println("El id no existe");
            return null;
        }
        return paciente;
    }

    public Paciente buscarByName(String nombre) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        Paciente paciente = (Paciente) sesion.createQuery("FROM Paciente WHERE nombre = :nomPac ").setParameter("nomPac", nombre).uniqueResult();
        transacion.commit();
        if (paciente == null) {
            System.out.println("El nombre no existe");
            return null;
        }
        return paciente;

    }

    public int borrar(Paciente paciente) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación        
        try {
            sesion.remove(paciente);
            transacion.commit();
            return paciente.getId(); // devuelve el id del paciente borrado
        } catch (Exception ex) {
            transacion.rollback();//para que no lo guarde si hay un error
            return 0;
        }
    }
}
