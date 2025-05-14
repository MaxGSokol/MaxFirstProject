package source;

import serves.ConsoleTools;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ClientServerConfig {
    public static volatile boolean IS_EXIT;
    private static final Properties PROPERTIES = new Properties();
    private static final String PATH = "src/source/client.config";
    public static String USER_NAME;
    public static String IP;
    public static int PORT;
    public static String SIGNATURE;
    public static String LOG_PATH;
    public static boolean IS_LOGGING;

    static {
        try (FileReader fileReader = new FileReader(PATH)) {
            PROPERTIES.load(fileReader);
            USER_NAME = PROPERTIES.getProperty("userName");
            IP = PROPERTIES.getProperty("ip");
            PORT = Integer.parseInt(PROPERTIES.getProperty("port"));
            SIGNATURE = PROPERTIES.getProperty("signature");
            LOG_PATH = PROPERTIES.getProperty("logPath");
            IS_LOGGING = Boolean.parseBoolean(PROPERTIES.getProperty("log"));
        } catch (IOException e) {
            ConsoleTools.exceptionMessage("Невозможно загрузить конфигурационный файл.\n" +
                    "Будет использовано значение по умолчанию.");
            USER_NAME = "DEFAUlT USER";

        }
    }

}
