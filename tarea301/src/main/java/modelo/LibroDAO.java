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
        String sql = "SELECT * FROM Libros";
        ArrayList<Libro> lista = new ArrayList<>();
        Libro lib = null; 
        try (Connection con = DB.getConnection();PreparedStatement stmt = con.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lib = new Libro(rs.getString(1), rs.getString(2), rs.getInt(3));
                lista.add(lib);
            }
        }
        return lista;
    }
}
