package server;

import server.db.Db;
import server.db.DbConfig;
import server.db.DbConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author dector (dector9@gmail.com)
 */
public class ClearDatabaseServlet extends HttpServlet {
    private static final String ATTR_DB_CONNECTOR = "server.db.connector";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Database

        HttpSession session = req.getSession();

        DbConnector db = (DbConnector)session.getAttribute(ATTR_DB_CONNECTOR);
        if (db == null) {
            DbConfig config = Db.getConfig();
            db = new DbConnector(config);

            session.setAttribute(ATTR_DB_CONNECTOR, db);
        }

        db.execute(String.format("TRUNCATE TABLE %s", "hosts"));

        // Response

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
