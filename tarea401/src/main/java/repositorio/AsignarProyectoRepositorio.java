package repositorio;

import entidades.AsignarProyecto;
import entidades.DatosProfesionales;
import entidades.Empleado;
import entidades.Proyecto;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alba_
 */
public class AsignarProyectoRepositorio implements Repositorio<AsignarProyecto, Object>{
    private Session sesion;

    public AsignarProyectoRepositorio(Session sesion) {
        this.sesion = sesion;
    }

    @Override
    public void insertar(AsignarProyecto ap) {
        Transaction trx = sesion.beginTransaction();
        sesion.save(ap);
        trx.commit();
    }

    @Override
    public void borrar(AsignarProyecto t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<AsignarProyecto> buscarAll() {
        Query query = sesion.createQuery("SELECT ap FROM AsignarProyecto ap");
        List<AsignarProyecto> listaJefes = query.getResultList();
        return listaJefes;
    }

    @Override
    public AsignarProyecto buscarById(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(AsignarProyecto ap) {
        Transaction trx = sesion.beginTransaction();
        sesion.update(ap);
        trx.commit();
    }

    public AsignarProyecto buscarById(Empleado e, Proyecto p) {
        Transaction trx = null;
        try {
            trx = sesion.getTransaction();
            if (trx == null || !trx.isActive()) {
                trx = sesion.beginTransaction();
            }
            Query query = sesion.createQuery("SELECT ap FROM AsignarProyecto ap WHERE ap.empleado=:emp AND ap.proyecto =:pro");
            query.setParameter("emp", e);
            query.setParameter("pro", p);
            AsignarProyecto asig = (AsignarProyecto) query.getSingleResult();
            trx.commit();
            return asig;
        } catch (Exception ex) {
            if (trx != null && trx.isActive()) {
                trx.rollback();
            }
            throw ex;
        }
    }
    
}
