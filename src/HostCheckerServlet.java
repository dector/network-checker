import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author dector (dector9@gmail.com)
 */
public class HostCheckerServlet extends HttpServlet {
    private static final String PARAM_HOST      = "host";
    private static final String PARAM_TIMEOUT   = "t";

    private static final String RESP_OK         = "OK";
    private static final String RESP_FAILED     = "Failed";

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String host = req.getParameter(PARAM_HOST);
        int timeOut = Integer.parseInt(req.getParameter(PARAM_TIMEOUT));

        resp.setContentType("text/plain");

        PrintWriter out = resp.getWriter();
        out.println(getResponseString(host, timeOut));
        out.flush();
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
