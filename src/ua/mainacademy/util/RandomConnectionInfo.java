package ua.mainacademy.util;

import ua.mainacademy.model.ConnectionInfo;

public class RandomConnectionInfo {
    private static int startId = 0;

    public static ConnectionInfo getRandomConnectionInfo() {
        int s = startId + 1;
        startId = s;
        ConnectionInfo randomInfo = new ConnectionInfo(s, System.currentTimeMillis(), generaTeRandomIp(s));
        return randomInfo;
    }

    private static String generaTeRandomIp(int i) {
        return "192.192.192." + i;
    }
}
