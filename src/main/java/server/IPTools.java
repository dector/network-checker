package server;

/**
 * @author dector (dector9@gmail.com)
 */
public class IPTools {
    public static void convertToUnsignedBytes(byte[] bytes) {
        byte byteVal;
        for (int i = 0; i < bytes.length; i++) {
            byteVal = bytes[i];

            if (byteVal <= 127) {
                bytes[i] = byteVal;
            } else {
                bytes[i] = (byte) (- byteVal - Byte.MIN_VALUE);
            }
        }
    }

    public static int getBitFormat(String ip) {
        int ip32bit = 0;

        String[] addrSegments = ip.split("\\.");

        int shiftCount = 0;
        for (int i = 4; i >= 0; i--) {
            ip32bit |= Integer.parseInt(addrSegments[i]) << shiftCount;
            shiftCount += 8;
        }

        return ip32bit;
    }

    public static byte[] getByteFormat(String ip) {
        String[] addrSegments = ip.split("\\.");

        byte[] byteAddr = new byte[addrSegments.length];

        for (int i = 0; i < addrSegments.length; i++) {
            byteAddr[i] = (byte)Integer.parseInt(addrSegments[i]);
        }

        convertToUnsignedBytes(byteAddr);
        
        return byteAddr;
    }
}
