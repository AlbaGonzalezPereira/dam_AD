package repositorios;

import entidades.Hospital;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alba_
 */
public class RepositorioHospital implements Repositorio<Hospital> {

    private Session sesion;

    //Creamos constructor con la sesion
    public RepositorioHospital(Session sesion) {
        this.sesion = sesion;
    }

    @Override
    public int crear(Hospital hospital) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        try {
            sesion.persist(hospital);
            transacion.commit();
            return hospital.getId(); // devuelve el id del hospital creado
        } catch (Exception ex) {
            transacion.rollback();//para que no lo guarde si hay un error
            return 0;
        }
    }

    @Override
    public int modificar(Hospital hospital) {
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
    public Hospital buscarById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Hospital buscarByName(String nombre) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        Hospital hospital = (Hospital) sesion.createQuery("FROM Hospital WHERE nombre = :nomHos ").setParameter("nomHos", nombre).uniqueResult();
        transacion.commit();
        if (hospital == null) {
            System.out.println("El nombre del hospital no existe");
            return null;
        }
        return hospital;
    }
    
    
}
