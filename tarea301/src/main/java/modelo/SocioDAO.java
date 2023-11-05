
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
public class SocioDAO {
     public static ArrayList<Socio> cargarSocios() throws SQLException {
        String sql = "SELECT * FROM socios";
        ArrayList<Socio> lista = new ArrayList<>();
        Socio soc = null; 
        try (Connection con = DB.getConnection();PreparedStatement stmt = con.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                soc = new Socio(rs.getString(1), rs.getString(2), rs.getString(3));
                lista.add(soc);
            }
        }
        return lista;
    }
}
