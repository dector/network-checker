import db.Db;
import db.DbConfig;
import db.DbConnector;

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
public class HostCheckerServlet extends HttpServlet {
    private static final String ATTR_DB_CONNECTOR = "db.connector";

    private static final String PARAM_HOST      = "h";
    private static final String PARAM_TIMEOUT   = "t";

    private static final String RESP_OK         = "On";
    private static final String RESP_FAILED     = "Off";

    private static final int DEFAULT_TIMEOUT    = 2000;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String host = req.getParameter(PARAM_HOST);
        String timeOutStr = req.getParameter(PARAM_TIMEOUT);
        int timeOut = (timeOutStr != null) ? Integer.parseInt(timeOutStr) : DEFAULT_TIMEOUT;

        System.out.printf("[REQUEST] Host: %s, timeout: %d%n", host, timeOut);

        // Database

        HttpSession session = req.getSession();

        DbConnector db = (DbConnector)session.getAttribute(ATTR_DB_CONNECTOR);
        if (db == null) {
            DbConfig config = Db.getConfig();
            db = new DbConnector(config);

            session.setAttribute(ATTR_DB_CONNECTOR, db);
        }

        // Response

        String responseString = getResponseString(host, timeOut);

        resp.setContentType("text/plain");
        resp.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = resp.getWriter();
        out.println(responseString);

        // Database "response"

        // Fucking PrepareStatement doesn't want work !!!
        ResultSet res = db.query(String.format("SELECT * FROM %s WHERE %s = '%s'",
                "hosts", "url", host));
        try {
            if (res != null) {
                if (! res.next()) { // no such host in database
                    db.execute(String.format("INSERT INTO %s VALUES ('%s', %b)",
                            "hosts", host, responseString == RESP_OK));
                } else {
                    db.execute(String.format("UPDATE %s SET %s = %b WHERE %s = '%s'",
                            "hosts", "state", responseString == RESP_OK, "url", host));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Close all resources

        out.close();
    }

    private String getResponseString(String host, int timeOut) {
        String responseString;

        if (HostChecker.isHostReachable(host, timeOut)) {
            responseString = RESP_OK;
        } else {
            responseString = RESP_FAILED;
        }

        return responseString;
    }

}
