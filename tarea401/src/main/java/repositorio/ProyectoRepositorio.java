package repositorio;

import entidades.Proyecto;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alba_
 */
public class ProyectoRepositorio implements Repositorio<Proyecto, Integer> {
    //insertamos el atributo sesion

    private Session sesion;

    public ProyectoRepositorio(Session sesion) {
        this.sesion = sesion;
    }

    @Override
    public void insertar(Proyecto p) {
        Transaction trx = sesion.beginTransaction();
        sesion.save(p);
        trx.commit();
    }

    @Override
    public void borrar(Proyecto p) {
        Transaction trx = sesion.beginTransaction();
        sesion.delete(p);
        trx.commit();
    }

    @Override
    public List<Proyecto> buscarAll() {
        Query query = sesion.createQuery("SELECT p FROM Proyecto p");
        List<Proyecto> listaProyectos = query.getResultList();
        return listaProyectos;
    }

    @Override
    public Proyecto buscarById(Integer id) {
        Transaction trx = null;
        try {
            trx = sesion.getTransaction();
            if (trx == null || !trx.isActive()) {
                trx = sesion.beginTransaction();
            }
            Query query = sesion.createQuery("SELECT p FROM Proyecto p WHERE p.id = :id");
            query.setParameter("id", id);
            Proyecto proy = (Proyecto) query.getSingleResult();
            trx.commit();
            return proy;
        } catch (Exception e) {
            if (trx != null && trx.isActive()) {
                trx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void actualizar(Proyecto p) {
        Transaction trx = sesion.beginTransaction();
        sesion.update(p);
        trx.commit();
    }

}
