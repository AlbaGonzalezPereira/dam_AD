package modelo;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alba_
 */

public class DB implements Closeable {

    private static DB db = null;
    public static String URL = "jdbc:mariadb://localhost:3306/tarea301?allowPublicKeyRetrieval=true&useSSL=false;";
    public static String USER = "root";
    public static String PASS = "";
    private static Connection con = null;

    public static DB open() throws SQLException {
        if (DB.db == null) {
            DB.db = new DB();
        }
        return DB.db;
    }

  public static Connection getConnection() {
        if (DB.db == null)
            throw new RuntimeException("Debes abrir antes la conexión con open");

        try {
            if (DB.db.con == null || con.isClosed())
                DB.db.connect();
            return DB.db.con;
        } catch (RuntimeException | SQLException ex) {
            throw new RuntimeException("Error obteniendo conexión BBDD: " + ex.getMessage());
        }
    }

    private DB() throws SQLException {
        connect();
    }

    private final void connect() throws SQLException {
        con = DriverManager.getConnection(DB.URL, DB.USER, DB.PASS);
    }

    @Override
    public void close() throws IOException {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }
}