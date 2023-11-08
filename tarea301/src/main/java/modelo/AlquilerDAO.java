
package modelo;

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
        String sql = "SELECT l.codigo, l.titulo, a.dniSocio, a.fechaAlquiler, MAX(a.fechaDevolucion)"
                + " FROM libros l"
                + " INNER JOIN alquiler a ON l.codigo = a.codigoLibro"
                + " GROUP BY l.codigo"
                + " HAVING MAX(a.fechaDevolucion) IS null";
        ArrayList<Alquiler> alquilados = new ArrayList<>();
        Alquiler alquilado = null;
        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Libro lib = new Libro(rs.getString(1), rs.getString(2));
                alquilado= new Alquiler(rs.getDate(4), rs.getString(3), lib);
                alquilados.add(alquilado);
            }
        }
        return alquilados;
    }
}
