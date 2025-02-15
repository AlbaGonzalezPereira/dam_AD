package repositorios;

import entidades.Doctor;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase que tendrá los métodos de la clase Doctor
 *
 * @author alba_
 */
public class RepositorioDoctor implements Repositorio<Doctor> {

    private Session sesion;

    //Creamos constructor con la sesion
    public RepositorioDoctor(Session sesion) {
        this.sesion = sesion;
    }

    @Override
    public int crear(Doctor doctor) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        try {
            sesion.persist(doctor);
            transacion.commit();
            return doctor.getId(); // devuelve el id del doctor creado
        } catch (Exception ex) {
            transacion.rollback();//para que no lo guarde si hay un error
            return 0;
        }
    }

    @Override
    public int modificar(Doctor doctor) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        try {
            sesion.merge(doctor);
            transacion.commit();
            return doctor.getId(); // devuelve el id del doctor modificado
        } catch (Exception ex) {
            transacion.rollback();//para que no lo guarde si hay un error
            return 0;
        }
    }

    @Override
    public int borrar(int id) {
        Doctor doctor = buscarById(id);
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        
        try {
            sesion.remove(doctor);
            transacion.commit();
            return doctor.getId(); // devuelve el id del doctor borrado
        } catch (Exception ex) {
            transacion.rollback();//para que no lo guarde si hay un error
            return 0;
        }
    }

    @Override
    public void mostrarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int devolverUltimoId() {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        Integer ultimoId = (Integer) sesion.createQuery("SELECT MAX(id) FROM Doctor").uniqueResult();
        transacion.commit();
        if (ultimoId == null) {
            return 0;
        }
        return ultimoId;
    }

    @Override
    public Doctor buscarById(int idDoc) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        Doctor doctor = (Doctor) sesion.createQuery("FROM Doctor WHERE id = :idDoc ").setParameter("idDoc", idDoc).uniqueResult();
        transacion.commit();
        if (doctor == null) {
            System.out.println("El id no existe");
            return null;
        }
        return doctor;        
    }
    
    public Doctor buscarByName(String nombre){
    Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        Doctor doctor = (Doctor) sesion.createQuery("FROM Doctor WHERE nombre = :nomDoc ").setParameter("nomDoc", nombre).uniqueResult();
        transacion.commit();
        if (doctor == null) {
            System.out.println("El nombre no existe");
            return null;
        }
        return doctor;
    }
}
