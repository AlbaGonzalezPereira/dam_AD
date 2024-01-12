package repositorio;

import entidades.Empleado;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alba_
 */
public class EmpleadoRepositorio implements Repositorio<Empleado, String> {

    //insertamos el atributo sesion
    private Session sesion;

    public EmpleadoRepositorio(Session sesion) {
        this.sesion = sesion;
    }

    @Override
    public void insertar(Empleado e) {
        Transaction trx = sesion.beginTransaction();
        sesion.save(e);
        trx.commit();
    }

    @Override
    public void borrar(Empleado e) {
        Transaction trx = sesion.beginTransaction();
        sesion.delete(e);
        trx.commit();
    }

    @Override
    public List<Empleado> buscarAll() {
        Query query = sesion.createQuery("SELECT e FROM Empleado e");
        List<Empleado> listaEmpleados = query.getResultList();
        return listaEmpleados;

    }

    @Override
    public Empleado buscarById(String id) {
        Transaction trx = null;
        try {
            trx = sesion.getTransaction();
            if (trx == null || !trx.isActive()) {
                trx = sesion.beginTransaction();
            }

            Query query = sesion.createQuery("SELECT e FROM Empleado e WHERE e.dni = :dni");
            query.setParameter("dni", id);
            Empleado emp = (Empleado) query.getSingleResult();
            trx.commit();
            return emp;
        } catch (Exception e) {
            if (trx != null && trx.isActive()) {
                trx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void actualizar(Empleado e) {
        Transaction trx = sesion.beginTransaction();
        sesion.update(e);
        trx.commit();

    }

}
