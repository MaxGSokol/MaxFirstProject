package storege;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientConfig {
    public static final Map<String, String> CLIENT_CONFIG = new ConcurrentHashMap<>();

    static {
        CLIENT_CONFIG.put("userName", " ИМПЕРАТОР ВСЕГО ЧЕЛОВЕЧЕСТВА");
        CLIENT_CONFIG.put("Ip", "localhost");
        CLIENT_CONFIG.put("Port", "4004");
    }

    public static volatile boolean IS_EXIT;
}
