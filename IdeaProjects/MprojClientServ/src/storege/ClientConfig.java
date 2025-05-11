package storege;

import serves.ConsoleTools;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ClientConfig {
    public static Properties PROPERTIES = new Properties();
    public static final String path = "src/client.config";
    public static volatile String USER_NAME;

    static {
        try (FileReader fileReader = new FileReader(path)) {
            PROPERTIES.load(fileReader);
            USER_NAME = PROPERTIES.getProperty("userName");
        } catch (IOException e) {
            ConsoleTools.exceptionMessage("Невозможно загрузить конфигурационный файл.\n" +
                    "Будет использовано значение по умолчанию.");
            USER_NAME = "DEFUlT USER";

        }
    }

    public static volatile boolean IS_EXIT;
    public static volatile boolean IS_SEND;
}
