package mfh.faztech.online_library.util;

public class DBInfo {
    private static String jdbcURL;
    private static String jdbcUsername;
    private static String jdbcPassword;

    static {
        jdbcURL = "jdbc:mysql://database:3306/new_db";
        jdbcUsername = "root";
        jdbcPassword = "123456mfh";
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
