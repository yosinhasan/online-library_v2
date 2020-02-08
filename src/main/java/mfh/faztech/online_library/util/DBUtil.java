package mfh.faztech.online_library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
    private static DBUtil dbUtil;

    {
        this.jdbcURL = DBInfo.getJdbcURL();
        this.jdbcUsername = DBInfo.getJdbcUsername();
        this.jdbcPassword = DBInfo.getJdbcPassword();
    }

    public static DBUtil getInstance() {
        if (dbUtil == null) {
            dbUtil = new DBUtil();
        }
        return dbUtil;
    }

    public Connection getJdbcConnection() {
        return jdbcConnection;
    }

    public void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    public void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

}
