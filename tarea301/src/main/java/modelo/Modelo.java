package modelo;

import exception.CodeNotFoundException;
import exception.HireNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * clase que realiza operaciones con la base de datos y contiene métodos para que
 * el controlador acceda a ellos
 * @author alba_
 */
public class Modelo {

    //insertamos el constructor
    public Modelo() throws SQLException {
        try {
            DB.open();
            //System.out.println("ok");//comprobamos
        } catch (SQLException ex) {
            System.out.println("Error en la conexión a la base de datos: " + ex.getMessage());
        }
    }

    /**
     * método que nos devuelve los datos de los socios
     * @return - ArrayLis de Socio
     */
    public ArrayList<Socio> obtenerDatosSocios() {
        try {
            return SocioDAO.cargarSocios();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * método que nos devuelve los libros disponibles
     * @return - ArrayLis de libro
     */
    public ArrayList<Libro> obtenerLibrosDisponibles() {

        try {
            return LibroDAO.cargarLibrosDisponibles();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * método que nos devuelve los libros alquilados
     * @return - ArrayList de Alquiler
     */
    public ArrayList<Alquiler> obtenerLibrosAlquilados() {
        try {
            return AlquilerDAO.cargarLibrosAlquilados();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * método que nos permite alquilar un libro
     * @param dni - dni socio
     * @param codigo - código del libro
     * @return - nº de libros alquilados
     * @throws HireNotFoundException 
     */
    public int anhadirLibroAlquilado(String dni, String codigo) throws HireNotFoundException {
        try {
            return LibroDAO.alquilarLibro(codigo, dni);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    /**
     * método que nos permite devolver un libro alquilado
     * @param codigo - código del libro alquilado
     * @return - nº de libros alquilados
     * @throws CodeNotFoundException 
     */
    public int devolverLibroAlquilado(String codigo) throws CodeNotFoundException {
        try {
            return AlquilerDAO.devolverLibro(codigo);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }
    
    /**
     * método que nos permite obtener el listado de los libros alquilados
     * @return - ArrayList de alquileres
     */
    public ArrayList<Alquiler> obtenerHistoricosAlquiler() {
        try {
            return AlquilerDAO.obtenerHistorico();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
