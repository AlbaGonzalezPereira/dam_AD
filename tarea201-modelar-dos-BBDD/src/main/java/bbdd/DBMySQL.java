package bbdd;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alba_
 */
public class DBMySQL implements Closeable{
    private static DBMySQL db = null;
    public static String URL = "jdbc:mysql://localhost:3306/tarefa201";
    public static String USER = "root";
    public static String PASS = "";
    private static Connection con = null;

    public static DBMySQL open() throws SQLException {
        if (DBMySQL.db == null) {
            DBMySQL.db = new DBMySQL();
        }
        return DBMySQL.db;
    }

    public static Connection getConnection() {
        if (DBMySQL.db == null) {
            throw new RuntimeException("Debes abrir antes la conexión con open");
        }

        try {
            if (DBMySQL.db.con == null || con.isClosed()) {
                DBMySQL.db.connect();
            }
            return DBMySQL.db.con;
        } catch (RuntimeException | SQLException ex) {
            throw new RuntimeException("Error obteniendo conexión BBDD: " + ex.getMessage());
        }
    }

    private DBMySQL() throws SQLException {
        connect();
    }

    private final void connect() throws SQLException {
        con = DriverManager.getConnection(DBMySQL.URL, DBMySQL.USER, DBMySQL.PASS);
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

