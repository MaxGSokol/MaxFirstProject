package storege;

import serves.ConsoleTools;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ClientConfig {
    public static Properties PROPERTIES = new Properties();
    public static final String path = "src/client.config";

    static {
        try (FileReader fileReader = new FileReader(path)) {
            PROPERTIES.load(fileReader);
        } catch (IOException e) {
            ConsoleTools.exceptionMessage("Невозможно загрузить конфигурационный файл.");
            IS_EXIT = true;
        }
    }

    public static volatile boolean IS_EXIT;
    public static volatile boolean IS_SEND;
}
