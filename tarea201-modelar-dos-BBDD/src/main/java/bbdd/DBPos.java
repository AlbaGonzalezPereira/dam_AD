package bbdd;

/**
 *
 * @author alba_
 */
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * clase que permite la conexión a la base de datos y que usa el patrón
 * singleton
 *
 * @author alba_
 */
public class DBPos implements Closeable {

    private static DBPos db = null;
    public static String URL = "jdbc:postgresql://localhost:5433/almacen";
    public static String USER = "postgres";
    public static String PASS = "alba1234";
    private static Connection con = null;

    public static DBPos open() throws SQLException {
        if (DBPos.db == null) {
            DBPos.db = new DBPos();
        }
        return DBPos.db;
    }

    public static Connection getConnection() {
        if (DBPos.db == null) {
            throw new RuntimeException("Debes abrir antes la conexión con open");
        }

        try {
            if (DBPos.db.con == null || con.isClosed()) {
                DBPos.db.connect();
            }
            return DBPos.db.con;
        } catch (RuntimeException | SQLException ex) {
            throw new RuntimeException("Error obteniendo conexión BBDD: " + ex.getMessage());
        }
    }

    private DBPos() throws SQLException {
        connect();
    }

    private final void connect() throws SQLException {
        con = DriverManager.getConnection(DBPos.URL, DBPos.USER, DBPos.PASS);
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
