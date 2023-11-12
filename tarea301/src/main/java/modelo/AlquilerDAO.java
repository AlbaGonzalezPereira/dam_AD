package modelo;

import exception.CodeNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * clase que contiene los métodos de consulta a la base de datos de la clase Alquiler 
 * @author alba_
 */
public class AlquilerDAO {

    /**
     * método que nos devuelve los libros alquilados y lanza una excepción en caso de error
     * @return - alquileres
     * @throws SQLException 
     */
    public static ArrayList<Alquiler> cargarLibrosAlquilados() throws SQLException {
        String sql = "SELECT l.codigo, l.titulo, a.dniSocio, a.fechaAlquiler,a.fechaDevolucion"
                + " FROM libros l"
                + " right JOIN alquiler a ON l.codigo = a.codigoLibro"
                + " WHERE a.fechaDevolucion IS null";
        ArrayList<Alquiler> alquilados = new ArrayList<>();
        Alquiler alquilado = null;
        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Libro lib = new Libro(rs.getString(1), rs.getString(2));
                alquilado = new Alquiler(rs.getDate(4), rs.getString(3), lib);
                alquilados.add(alquilado);
            }
        }
        return alquilados;
    }

    /**
     * método que permite devolver un libro alquilado
     * @param codigo
     * @return - nº filas afectadas
     * @throws SQLException
     * @throws CodeNotFoundException 
     */
    public static int devolverLibro(String codigo) throws SQLException, CodeNotFoundException {
        ArrayList<Alquiler> librosAlquilados = AlquilerDAO.cargarLibrosAlquilados();
        boolean encontrado = false;
        for (Alquiler librosAlquilado : librosAlquilados) {
            if (librosAlquilado.getLibro().getCodigo().equals(codigo)) {
                encontrado = true;
            }
        }
        if (!encontrado) {
            throw new CodeNotFoundException();//lanzamos la excepción
        }  
        //declaramos la consulta
        String sql = "UPDATE alquiler SET fechaDevolucion=(SELECT CURDATE() AS ‘DateAndTime’) WHERE codigoLibro=? AND fechaDevolucion IS NULL;";

        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setString(1, codigo);
            return stmt.executeUpdate();//devuelve el número de registros (filas) afectadas
        }
    }

    /**
     * método que nos devuelve el histórico (los registros) de los alquileres
     * @return - ArrayList con los alquileres
     * @throws SQLException 
     */
    public static ArrayList<Alquiler> obtenerHistorico() throws SQLException {
        String sql = "SELECT codigoLibro, dniSocio, fechaAlquiler, fechaDevolucion FROM alquiler;";
        ArrayList<Alquiler> historicos = new ArrayList<>();
        Alquiler historico = null;
        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                historico = new Alquiler(rs.getDate(3), rs.getDate(4), rs.getString(2), new Libro(rs.getString(1)));
                historicos.add(historico);
            }
        }
        return historicos;
    }
}
