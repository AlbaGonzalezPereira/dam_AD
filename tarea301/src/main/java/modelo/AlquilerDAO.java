package modelo;

import exception.CodeNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alba_
 */
public class AlquilerDAO {

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

    public static int devolverLibro(String codigo) throws SQLException, CodeNotFoundException {
//        String sql = "UPDATE alquiler SET fechaDevolucion=? WHERE codigoLibro=codigo AND fechaDevolucion=null;";
        ArrayList<Alquiler> librosAlquilados = AlquilerDAO.cargarLibrosAlquilados();
        boolean encontrado = false;
        for (Alquiler librosAlquilado : librosAlquilados) {
            if (librosAlquilado.getLibro().getCodigo().equals(codigo)) {
                encontrado = true;
            }
        }
        if (!encontrado) {
            throw new CodeNotFoundException();//lanzamos la excepción
        }            //return -1;
        String sql = "UPDATE alquiler SET fechaDevolucion=(SELECT CURDATE() AS ‘DateAndTime’) WHERE codigoLibro=? AND fechaDevolucion IS NULL;";

        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setString(1, codigo);
            return stmt.executeUpdate();
        }

    }

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
