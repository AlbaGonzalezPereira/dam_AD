package repositorios;

import entidades.Paciente;
import entidades.Recibe;
import entidades.Tratamiento;
import java.time.LocalDate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alba_
 */
public class RepositorioRecibe implements Repositorio<Recibe>{
    private Session sesion;

    //Creamos constructor con la sesion
    public RepositorioRecibe(Session sesion) {
        this.sesion = sesion;
    }
    
    public void indicarFinTratamiento(String paciente, String tipo, LocalDate fechaInicio, LocalDate fechaFin ){
        RepositorioPaciente repPac = new RepositorioPaciente(sesion);
        RepositorioTratamiento repTra = new RepositorioTratamiento(sesion);
        Paciente pac = repPac.buscarByName(paciente);
        Tratamiento tra = repTra.buscarByName(tipo);
        
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        try {           
            Recibe rec=new Recibe(pac, tra, fechaInicio, fechaFin);
            sesion.merge(rec);
            transacion.commit();
            
        } catch (Exception ex) {
            transacion.rollback();//para que no lo guarde si hay un error
            System.out.println("No se pudo crear un registro en recibe");
        }
    }

    @Override
    public int crear(Recibe t) {
        Transaction transacion = sesion.beginTransaction(); // para comenzar una transación
        try {
            sesion.persist(t);
            transacion.commit();
            return 1; // devuelve el id del recibe creado
        } catch (Exception ex) {
            transacion.rollback();//para que no lo guarde si hay un error
            return 0;
        }
    }

    @Override
    public int modificar(Recibe t) {
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
    public Recibe buscarById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Recibe buscarByName(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
