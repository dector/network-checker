import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author dector (dector9@gmail.com)
 */
public class DbHandler {
    private String url;
    private String user;
    private String password;

    private Connection conn;

    public DbHandler(String url, String user, String password)
            throws ClassNotFoundException {
        this.url = url;
        this.user = user;
        this.password = password;

        Class.forName("org.h2.Driver");
    }

    public void connect() throws SQLException {
        close();

        conn = DriverManager.getConnection(url, user, password);
    }

    public void close() throws SQLException {
        if (conn == null) return;
        if (conn.isClosed()) return;

        conn.close();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
