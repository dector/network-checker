import db.DbConfig;
import db.DbConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author dector (dector9@gmail.com)
 */
public class HostCheckerServlet extends HttpServlet {
    private static final String ATTR_DB_CONNECTOR = "db.connector";

    private static final String PARAM_HOST      = "h";
    private static final String PARAM_TIMEOUT   = "t";

    private static final String RESP_OK         = "Active";
    private static final String RESP_FAILED     = "Not Active";

    private static final String DB_URL          = "host-checker";
    private static final String DB_USER         = "user";
    private static final String DB_PASS         = "pass";

    private static final int DEFAULT_TIMEOUT    = 1000;

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
            DbConfig config = new DbConfig(DB_URL, DB_USER, DB_PASS);
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

        if (responseString.equals(RESP_OK)) {
//            db.query();
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
