package modelo;

import exception.HireNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * clase que contiene los métodos de consulta a la base de datos de la clase Libro
 * @author alba_
 */
public class LibroDAO {

    /**
     * método que nos devuelve los libros disponibles
     * @return - ArrayList de Libro
     * @throws SQLException 
     */
    public static ArrayList<Libro> cargarLibrosDisponibles() throws SQLException {
        //declaramos la consulta sql
        String sql = "SELECT l.codigo, l.titulo, l.autor, a.dniSocio, a.fechaAlquiler,a.fechaDevolucion"
                + " FROM libros l"
                + " LEFT JOIN alquiler a ON l.codigo = a.codigoLibro"
                + " WHERE l.codigo NOT IN (SELECT l.codigo"
                + " FROM libros l"
                + " RIGHT JOIN alquiler a ON l.codigo = a.codigoLibro"
                + " WHERE a.fechaDevolucion IS NULL) OR l.codigo IN (SELECT l.codigo"
                + " FROM libros l"
                + " LEFT JOIN alquiler a ON l.codigo = a.codigoLibro WHERE a.id IS NULL)"
                + " GROUP BY l.codigo;";
        ArrayList<Libro> lista = new ArrayList<>();
        Libro lib = null;
        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lib = new Libro(rs.getString(1), rs.getString(2), rs.getString(3));
                lista.add(lib);
            }
        }
        return lista;
    }

    /**
     * método que nos permite alquilar un libro y lanza una excepción en caso de no
     * poder alquilarlo (no estar disponible)
     * @param codigo
     * @param dni - dni socio
     * @return - nº de filas afectadas
     * @throws SQLException
     * @throws HireNotFoundException 
     */
    public static int alquilarLibro(String codigo, String dni) throws SQLException, HireNotFoundException {
        ArrayList<Libro> librosDisponibles = cargarLibrosDisponibles();
        boolean encontrado = false;

        //comprobamos que haya algún libro disponible
        for (Libro librosDisponible : librosDisponibles) {
            if (librosDisponible.getCodigo().equals(codigo)) {
                encontrado = true;
            }
        }

        //si no encontró el código del libro disponible, devuelve -1
        if (encontrado == false) {
            throw new HireNotFoundException(); //lanzamos la excepción
            //return -2;//comprobamos
        }
        //Declaramos consulta 
        String sql = "INSERT INTO alquiler (fechaAlquiler, fechaDevolucion, dniSocio, codigoLibro) VALUES (?,null,?,?);";
        //Declaramos conexion e a clase para modificar os valores
        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            //agregamos los valores
            stmt.setDate(1, Date.valueOf(LocalDate.now())); //Primer value
            stmt.setString(2, dni);
            stmt.setString(3, codigo);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }
    }

}
