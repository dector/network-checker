package server.db;

import java.io.Serializable;

/**
 * Database connection configuration
 *
 * @author dector
 * @version 25.11.10 0:55
 */
public class DbConfig implements Serializable {
    private String url;
    private String user;
    private String password;

    public DbConfig(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public DbConfig() {
        this("", "", "");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}

