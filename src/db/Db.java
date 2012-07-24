package db;

/**
 * @author dector (dector9@gmail.com)
 */
public class Db {
    private static final String DB_URL  = "jdbc:h2:checker" + ";MULTI_THREADED=1";
    private static final String DB_USER = "user";
    private static final String DB_PASS = "pass";

    private static DbConfig config;

    private Db() {}

    public static DbConfig getConfig() {
        if (config == null) config = new DbConfig(DB_URL, DB_USER, DB_PASS);

        return config;
    }
}
