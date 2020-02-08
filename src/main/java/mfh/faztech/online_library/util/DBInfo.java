package mfh.faztech.online_library.util;

public class DBInfo {
    private static String jdbcURL;
    private static String jdbcUsername;
    private static String jdbcPassword;

    static {
        jdbcURL = "jdbc:mysql://localhost:3306/new_db";
        jdbcUsername = "root";
        jdbcPassword = "111111frost";
    }

    public static String getJdbcURL() {
        return jdbcURL;
    }

    public static String getJdbcUsername() {
        return jdbcUsername;
    }

    public static String getJdbcPassword() {
        return jdbcPassword;
    }
}
