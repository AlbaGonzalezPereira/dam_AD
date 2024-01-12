package repositorio;

import entidades.DatosProfesionales;
import entidades.Empleado;
import org.hibernate.Session;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author alba_
 */
public class DatosProfesionalesRepositorio implements Repositorio<DatosProfesionales, String> {
    private Session sesion;

    public DatosProfesionalesRepositorio(Session sesion) {
        this.sesion = sesion;
    }

    @Override
    public void insertar(DatosProfesionales t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrar(DatosProfesionales t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DatosProfesionales> buscarAll() {
        Query query = sesion.createQuery("SELECT dp FROM DatosProfesionales dp");
        List<DatosProfesionales> listaDatos = query.getResultList();
        return listaDatos;
    }

    @Override
    public DatosProfesionales buscarById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(DatosProfesionales t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}