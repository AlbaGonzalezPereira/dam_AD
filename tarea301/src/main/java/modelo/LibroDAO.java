/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class LibroDAO {

    public static ArrayList<Libro> cargarLibrosDisponibles() throws SQLException {
        String sql = "SELECT l.codigo, l.titulo, a.fechaAlquiler,l.autor, MAX(a.fechaDevolucion)"
                + " FROM libros l"
                + " LEFT JOIN alquiler a ON l.codigo = a.codigoLibro"
                + " GROUP BY l.codigo"
                + " HAVING a.fechaAlquiler IS NULL OR MAX(a.fechaDevolucion) IS NOT NULL;";
        ArrayList<Libro> lista = new ArrayList<>();
        Libro lib = null;
        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lib = new Libro(rs.getString(1), rs.getString(2), rs.getString(4));
                lista.add(lib);
            }
        }
        return lista;
    }

    

}
