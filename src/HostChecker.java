import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author dector (dector9@gmail.com)
 */
public class HostChecker {
    public static boolean isHostReachable(String hostAddress, int timeout) {
        boolean reachable = false;
        
        String[] addrSegments = hostAddress.split("\\.");

        byte[] byteAddr = new byte[addrSegments.length];

        int byteVal;
        for (int i = 0; i < addrSegments.length; i++) {
            byteVal = Integer.parseInt(addrSegments[i]);

            if (byteVal <= 127) {
                byteAddr[i] = (byte) byteVal;
            } else {
                byteAddr[i] = (byte) (- byteVal - Byte.MIN_VALUE);
            }
        }

        try {
            InetAddress addr = InetAddress.getByAddress(byteAddr);

            if (addr.isReachable(timeout)) reachable = true;
        } catch (UnknownHostException e) {
            System.err.printf("Host %s not found.%n", hostAddress);
        } catch (IOException e) {
            System.err.printf("Host %s timeout overlimited.%n", hostAddress);
        }

        return reachable;
    }
}
