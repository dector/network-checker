import org.eclipse.jetty.server.Server;

/**
 * @author dector (dector9@gmail.com)
 */
public class TestServer {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        Server server = new Server(PORT);
        server.setHandler(new HostCheckerHandler());

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
