/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author alba_
 */
public class LibroDAO {

    public static ArrayList<Libro> cargarLibrosDisponibles() throws SQLException {
        String sql = "SELECT l.codigo, l.titulo, a.fechaAlquiler,l.autor, MAX(a.fechaDevolucion)"
                + " FROM libros l"
                + " LEFT JOIN alquiler a ON l.codigo = a.codigoLibro"
                + " GROUP BY l.codigo"
                + " HAVING a.fechaAlquiler IS NULL OR (MAX(a.fechaDevolucion) IS NOT NULL"
                + " AND MAX(a.fechaDevolucion)>=MAX(a.fechaAlquiler));";
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

    public static int alquilarLibro(String codigo, String dni) throws SQLException, HireNotFoundException{
        ArrayList<Libro> librosDisponibles = cargarLibrosDisponibles();
        boolean encontrado = false;
        
        //comprobamos que haya algún libro disponible
        for (Libro librosDisponible : librosDisponibles) {
            if(librosDisponible.getCodigo().equals(codigo))
                encontrado = true;   
        }
        
        //si no encontró el código del libro disponible, devuelve -1
        if(encontrado==false){
            throw new HireNotFoundException(); //lanzamos la excepción
            //return -2;
        }
        //Declaramos consulta 
        String sql = "INSERT INTO alquiler (fechaAlquiler, fechaDevolucion, dniSocio, codigoLibro) VALUES (?,null,?,?);";
        //Declaramos conexion e a clase para modificar os valores
        try (Connection con = DB.getConnection();PreparedStatement stmt = con.prepareStatement(sql);) {
            //agregamos los valores
            stmt.setDate(1, Date.valueOf(LocalDate.now())); //Primer value
            stmt.setString(2, dni);
            stmt.setString(3, codigo);
            return stmt.executeUpdate();
        }catch(SQLException ex){
            return -1;
        }   
    }
    
    

}
