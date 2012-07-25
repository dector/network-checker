package jetty;

import org.eclipse.jetty.server.Server;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.HostCheckerServlet;
import server.HostsListServlet;

/**
 * @author dector (dector9@gmail.com)
 */
public class TestServer {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        Server server = new Server(PORT);

        ServletContextHandler rootContext =
                new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        rootContext.addServlet(new ServletHolder(new HostCheckerServlet()), "/checker");
        rootContext.addServlet(new ServletHolder(new HostsListServlet()), "/hosts-list");
        rootContext.addServlet(new ServletHolder(new IndexPageServlet()), "/index.html");
        rootContext.addServlet(new ServletHolder(new JQueryFileServlet()), "/jquery.js");

        try {
            server.start();
            server.join();
        } catch (InterruptedException e) {
            System.err.println("Server interrupted");
        } catch (Exception e) {
            System.err.println("Server error");
        }
    }
}
