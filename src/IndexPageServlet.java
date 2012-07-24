import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 * @author dector (dector9@gmail.com)
 */
public class IndexPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StringBuilder webPage = new StringBuilder();
        Scanner in = new Scanner(new FileInputStream("web/index.html"));

        while(in.hasNextLine()) {
            webPage.append(in.nextLine()).append("\n");
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(webPage);
        resp.getWriter().close();
    }
}
