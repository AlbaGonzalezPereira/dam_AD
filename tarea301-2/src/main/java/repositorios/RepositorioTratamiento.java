
package repositorios;

import entidades.Hospital;
import entidades.Tratamiento;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alba_
 */
public class RepositorioTratamiento implements Repositorio<Tratamiento>{
    private Session sesion;

    //Creamos constructor con la sesion
    public RepositorioTratamiento(Session sesion) {
        this.sesion = sesion;
    }
    @Override
    public int crear(Tratamiento tratamiento) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        try {
            sesion.persist(tratamiento);
            transacion.commit();
            return tratamiento.getId(); // devuelve el id del tratamiento creado
        } catch (Exception ex) {
            transacion.rollback();//para que no lo guarde si hay un error
            return 0;
        }
    }

    @Override
    public int modificar(Tratamiento tratamiento) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        try {
            sesion.merge(tratamiento);
            transacion.commit();
            return tratamiento.getId(); // devuelve el id del tratamiento modificado
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Tratamiento buscarById(int id) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        Tratamiento tratamiento = (Tratamiento) sesion.createQuery("FROM Tratamiento WHERE id = :idTr ").setParameter("idTr", id).uniqueResult();
        transacion.commit();
        if (tratamiento == null) {
            System.out.println("El id no existe");
            return null;
        }
        return tratamiento;  
    }
    
    public Tratamiento buscarByName(String nombre) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        Tratamiento tratamiento = (Tratamiento) sesion.createQuery("FROM Tratamiento WHERE tipo = :tipo ").setParameter("tipo", nombre).uniqueResult();
        transacion.commit();
        if (tratamiento == null) {
            System.out.println("El tipo no existe");
            return null;
        }
        return tratamiento;

    }
    
    public int cambiarHospital(int idTrat, String hospitalActual, String hospitalNuevo){
        RepositorioHospital repoHos = new RepositorioHospital(sesion);
        Tratamiento tratamiento = buscarById(idTrat);
        Hospital hospitalAct = repoHos.buscarByName(hospitalActual);
        Hospital hospitalNew = repoHos.buscarByName(hospitalNuevo);
        if(hospitalNew!=null){
            tratamiento.setHospital(hospitalNew);
        }else{
            repoHos.crear(hospitalNew);
            tratamiento.setHospital(hospitalNew);
        }
        return modificar(tratamiento);
    }
    
    public int totalTratamientosByHospital(String nombre){
        RepositorioHospital repoHos = new RepositorioHospital(sesion);
        Hospital hospital = repoHos.buscarByName(nombre);
    
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        int tratamientos = (Integer) sesion.createQuery("SELECT COUNT(*) FROM Tratamiento WHERE id_hospital =:idH GROUP BY id_hospital").setParameter("idH", hospital.getId()).uniqueResult();
        transacion.commit();
        if (tratamientos == 0) {
            System.out.println("No hay tratamientos");
            return 0;
        }
        return tratamientos;  
    }
}
