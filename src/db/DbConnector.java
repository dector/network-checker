package db;

import java.io.Serializable;
import java.sql.*;

/**
 * Processing database connections class
 *
 * @author dector
 * @version 25.11.10 1:02
 */
public class DbConnector implements Serializable {
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:";
    private String CONNECTION_URL;

    private DbConfig conf;

    private Statement st;
    private PreparedStatement pst;

    /**
     * Init DbConnector with defined configuration
     *
     * @param conf database configuration class
     */
    public DbConnector(DbConfig conf) {
        this.conf = conf;
        CONNECTION_URL = getConnectionURL();
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Form database connection URL
     *
     * @return full connection URL
     */
    private String getConnectionURL() {
        return URL + conf.getUrl() + ":" +
                "?user=" + conf.getUser() +
                "&password=" + conf.getPassword();
    }

    /**
     * Execute query to database.<br>
     * Use this method for queries, like <b>SELECT</b> (that returns set of records).
     *
     * @param query SQL qery to execute
     * @return ResultSet of records
     */
    public ResultSet query(String query) {
        ResultSet rs = null;

        try {
            Connection con = DriverManager.getConnection(CONNECTION_URL);
            if (!con.isClosed()) {
                st = con.createStatement();
                rs = st.executeQuery(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    /**
     * Execute query to database.<br>
     * Use this method for queries, like <b>SELECT</b> (that returns set of records).<br><br>
     * Example:<br>
     * <pre>
     *      DbConnector.query("SELECT * FROM cars WHERE color = ?", new Object[] {"green"});
     * </pre>
     *
     * @param query SQL qery to execute
     * @param params array of values for "?"s in query
     * @return ResultSet of records
     */
    public ResultSet query(String query, Object[] params) {
        ResultSet rs = null;

        try {
            Connection con = DriverManager.getConnection(CONNECTION_URL);
            if (!con.isClosed()) {
                pst = con.prepareStatement(query);

                int i = 1;
                for (Object param : params) {
                    pst.setObject(i++, param);
                }

                rs = pst.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    /**
     * Execute query to database.<br>
     * Use this method for queries, like <b>UPDATE, INSERT, DELETE</b>.
     *
     * @param query SQL qery to execute
     * @return number of affected rows
     */
    public int execute(String query) {
        int res = -1;

        try {
            Connection con = DriverManager.getConnection(CONNECTION_URL);
            if (!con.isClosed()) {
                st = con.createStatement();
                res = st.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * Execute query to database.<br>
     * Use this method for queries, like <b>UPDATE, INSERT, DELETE</b>.<br><br>
     * Example:<br>
     * <pre>
     *      DbConnector.query("INSERT INTO cars (color) VALUES (?, ?)", new Object[] {"yellow", "white"});
     * </pre>
     *
     * @param query SQL qery to execute
     * @param params array of values for "?"s in query
     * @return ResultSet of records
     */
    public int execute(String query, Object[] params) {
        int res = -1;

        try {
            Connection con = DriverManager.getConnection(CONNECTION_URL);
            if (!con.isClosed()) {
                pst = con.prepareStatement(query);

                int i = 0;
                for (Object param : params) {
                    pst.setObject(i++, param);
                }

                res = pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * Close all connections with database
     */
    public void close() {
        try {
            if (st != null) {
                st.getConnection().close();
                st.close();
            }
            if (pst != null) {
                pst.getConnection().close();
                pst.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
