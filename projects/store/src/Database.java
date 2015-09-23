import com.mysql.jdbc.Connection;

import java.sql.*;
import java.sql.DriverManager;

/**
 * Provide access to MySQL Database
 *
 * @version 1.0
 * @since 12/3/12, 6:49 PM
 */
public final class Database {
    public static java.sql.Connection conn;
    public static Database db;

    /**
     * Конструктор. Соединение с базой
     * <p/>
     * Позволяет подключаться как к SQLite, так и к MySQL
     */
    private Database() {
        String dbType = Configurator.get().db.type;
        String url = "", dbName = Configurator.get().db.dbName;
        try {
            if (dbType.equals("mysql")) {
                url = "jdbc:mysql://localhost:3306/" + dbName;
                String userName = Configurator.get().db.user;
                String password = Configurator.get().db.pass;
                Database.conn = DriverManager.getConnection(url, userName, password);
            } else if (dbType.equals("sqlite")) {
                url = "jdbc:sqlite:" + dbName + ".db";
                Class.forName("org.sqlite.JDBC").newInstance();
                Database.conn = DriverManager.getConnection(url);
            }
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * Синглтон для соединения с базой
     *
     * @return Database Database connection object
     */
    public static synchronized java.sql.Connection con() {
        if (db == null) {
            db = new Database();
        }
        return db.conn;
    }

    /**
     * Количество полученных строк в запросе
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public static int count(ResultSet rs) throws SQLException {
        int count = 0;
        while (rs.next()) {
            count++;
        }
        return count;
    }
}