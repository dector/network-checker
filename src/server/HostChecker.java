package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author dector (dector9@gmail.com)
 */
public class HostChecker {
    public static boolean isHostReachable(String hostAddress, int timeout) {
        boolean reachable = false;
        
        byte[] byteAddr = IPTools.getByteFormat(hostAddress);

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
