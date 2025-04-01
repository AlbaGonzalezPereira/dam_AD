
package repositorios;

import entidades.Cita;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alba_
 */
public class RepositorioCita implements Repositorio<Cita>{
    private Session sesion;

    //Creamos constructor con la sesion
    public RepositorioCita(Session sesion) {
        this.sesion = sesion;
    }
    
    @Override
    public int crear(Cita cita) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        try {
            sesion.persist(cita);
            transacion.commit();
            return 1; // devuelve el id del doctor creado
        } catch (Exception ex) {
            transacion.rollback();//para que no lo guarde si hay un error
            return 0;
        }
    }

    @Override
    public int modificar(Cita t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cita buscarById(int id) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        Cita cita = (Cita) sesion.createQuery("FROM Cita WHERE id = :idC ").setParameter("idC", id).uniqueResult();
        transacion.commit();
        if (cita == null) {
            System.out.println("El id no existe");
            return null;
        }
        return cita;  
    }

    @Override
    public Cita buscarByName(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
