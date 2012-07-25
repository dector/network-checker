import junit.framework.TestCase;
import server.HostChecker;

/**
 * @author dector (dector9@gmail.com)
 */
public class HostCheckerTest extends TestCase {
    private static final int HOST_TIMEOUT = 100;
    private String[] hosts;

    public void setUp() throws Exception {
        hosts = new String[] {
                "127.0.0.1",
                "10.112.112.4",
                "10.112.112.5",
                "10.112.112.188",
                "10.112.112.189"
        };
    }

    public void testReachableMethod() throws Exception {
        for (String host : hosts) {
            System.out.println(HostChecker.isHostReachable(host, HOST_TIMEOUT));
        }

        assertTrue(true);   // =)
    }
}
